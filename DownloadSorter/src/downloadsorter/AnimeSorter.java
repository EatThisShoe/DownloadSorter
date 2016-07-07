package downloadsorter;



import java.nio.file.*;
import java.io.*;
import java.util.*;

public class AnimeSorter implements Runnable {
    Path sourceFolder;
    
    public AnimeSorter(SorterInitializers init) {
        sourceFolder = init.sourceFolder;
    }

    public void run() {
        List<FileName> episodeList = new ArrayList<>();
        List<FileName> animeList = new ArrayList<>();
       
        try (DirectoryStream<Path> unsortedFiles = Files.newDirectoryStream(sourceFolder)){
            for(Path afile: unsortedFiles){
                if(Files.isReadable(afile))
                    episodeList.add(new FileName(afile));
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        animeList = listAnimeToMove(episodeList);
        moveDuplicates(animeList);
       
        for(FileName fileout: animeList)
           System.out.println(fileout.toString());
    }
    
    public List<FileName> listAnimeToMove(List<FileName> l) {
        List<FileName> returnedList = new ArrayList<>();
        for(int i = 0; i < l.size(); i++) {
            FileName eachFile = l.get(i);
            for (int n = 0; n < l.size(); n++) {
                FileName sameSeries = l.get(n);
                if (eachFile.seriesName.equals(sameSeries.seriesName)
                    && eachFile.location != sameSeries.location){
                    if (!Files.isDirectory(eachFile.location)){
                        if (eachFile.hasFansubFormat){
                            returnedList.add(l.get(i));
                        }
                    } else {
                        
                    }
                }     
            }
        }
        return returnedList;
    }
    
    public void moveDuplicates(List<FileName> l) {
        for (FileName f: l){
            Path seriesFolder = Paths.get(sourceFolder + "\\" + f.seriesName);
            if(!Files.exists(seriesFolder)) {
                try {Files.createDirectory(seriesFolder);}
                catch(Exception e) {System.out.println(e.getMessage());}
            }
            String destination = f.location.getParent()+ "\\" + f.seriesName + "\\" + f.location.getFileName();
            Path inDir = Paths.get(destination);
            try {Files.move(f.location, inDir);}
            catch(Exception e) {System.out.println(e.getMessage());}
        }
    }
}

