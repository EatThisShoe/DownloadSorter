/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.Filters;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author Eric
 */
public class DestinationNamedDirectories implements DestinationRule {
    Path baseDirectory;
    
    public DestinationNamedDirectories(String[] fileInput) {
        baseDirectory = Paths.get(fileInput[1]);
    }
    
    public DestinationNamedDirectories(Path p) {
        baseDirectory = p;
    }

    @Override
    public void moveFiles(List<FileMetaData> l) {
        for (FileMetaData file: l){
            Path seriesFolder = baseDirectory.resolve(file.getName());
            if(!Files.exists(seriesFolder)) {
                try {Files.createDirectory(seriesFolder);}
                catch(Exception e) {System.out.println(e.getMessage());}
            }
            Path inDir = seriesFolder.resolve(file.getPath().getFileName());
            try {Files.move(file.getPath(), inDir);}
            catch(Exception e) {System.out.println(e.getMessage());}
        }
    }
    
    @Override
    public String toString() {
        String s = "DestinationNamedDirectories,";
        s.concat(baseDirectory.toString());
        return s;
    }
}
