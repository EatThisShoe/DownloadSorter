package downloadsorter;



import downloadsorter.Filters.AnimeEpisode;
import java.nio.file.*;
import java.util.*;

public class AnimeSorter implements Runnable {
    Path sourceFolder;
    
    public AnimeSorter(Settings init) {
        sourceFolder = init.sourceFolder;
    }

    public void run() {
        List<AnimeEpisode> episodeList = new ArrayList<>();
        List<AnimeEpisode> animeList = new ArrayList<>();
       
        try (DirectoryStream<Path> unsortedFiles = Files.newDirectoryStream(sourceFolder)){
            for(Path afile: unsortedFiles){
                if(Files.isReadable(afile))
                    episodeList.add(new AnimeEpisode(afile));
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        animeList = listAnimeToMove(episodeList);
        moveDuplicates(animeList);
       
        for(AnimeEpisode fileout: animeList)
           System.out.println(fileout.toString());
    }
    
    public List<AnimeEpisode> listAnimeToMove(List<AnimeEpisode> l) {
        List<AnimeEpisode> returnedList = new ArrayList<>();
        for(int i = 0; i < l.size(); i++) {
            AnimeEpisode eachFile = l.get(i);
            for (int n = 0; n < l.size(); n++) {
                AnimeEpisode sameSeries = l.get(n);
                if (eachFile.getName().equals(sameSeries.getName())
                    && eachFile.getPath() != sameSeries.getPath()){
                    if (!Files.isDirectory(eachFile.location)){
                        if (eachFile.isMatch()){
                            returnedList.add(l.get(i));
                        }
                    } else {
                        
                    }
                }     
            }
        }
        return returnedList;
    }
    
    public void moveDuplicates(List<AnimeEpisode> l) {
        for (AnimeEpisode f: l){
            Path seriesFolder = Paths.get(sourceFolder + "\\" + f.getName());
            if(!Files.exists(seriesFolder)) {
                try {Files.createDirectory(seriesFolder);}
                catch(Exception e) {System.out.println(e.getMessage());}
            }
            String destination = f.getPath().getParent()+ "\\" + f.getName() + "\\" + f.getPath().getFileName();
            Path inDir = Paths.get(destination);
            try {Files.move(f.getPath(), inDir);}
            catch(Exception e) {System.out.println(e.getMessage());}
        }
    }
}

