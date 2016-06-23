package downloadsorter;



import java.nio.file.*;
import java.io.*;
import java.util.*;

public class AnimeSorter {
    static Path torrentFolderPath = Paths.get("D:\\Torrents");

    public static void main(String[] args) throws IOException {
        
       
        List<FileName> episodeList = new ArrayList<>();
        List<FileName> animeList = new ArrayList<>();
       
        try (DirectoryStream<Path> unsortedFiles = Files.newDirectoryStream(torrentFolderPath)){
            for(Path afile: unsortedFiles){
                if(Files.isReadable(afile))
                    episodeList.add(new FileName(afile));
            }
        }
        
        animeList = listAnimeToMove(episodeList);
        moveDuplicates(animeList);
       
        for(FileName fileout: animeList)
           System.out.println(fileout.toString());
    }
    
    public static List<FileName> listAnimeToMove(List<FileName> l) {
        List<FileName> returnedList = new ArrayList<>();
        for(int i = 0; i < l.size(); i++) {
            FileName eachFile = l.get(i);
            for (int n = 0; n < l.size(); n++) {
                FileName sameSeries = l.get(n);
                if (eachFile.seriesName.equals(sameSeries.seriesName)){
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
    
    public static void moveDuplicates(List<FileName> l) throws IOException {
        for (FileName f: l){
            Path seriesFolder = Paths.get(torrentFolderPath + "\\" + f.seriesName);
            if(!Files.exists(seriesFolder))
                Files.createDirectory(seriesFolder);
            String destination = f.location.getParent()+ "\\" + f.seriesName + "\\" + f.location.getFileName();
            Path inDir = Paths.get(destination);
            try {Files.move(f.location, inDir);}
            catch(Exception e) {System.out.println(e.getMessage());}
        }
    }
}

