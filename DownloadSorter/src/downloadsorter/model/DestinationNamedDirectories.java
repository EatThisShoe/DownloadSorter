/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.model;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author Eric
 */
public class DestinationNamedDirectories implements DestinationRule, Rule {
    final static String fxmlPath = "view/rulepanes/DestinationNamedDirectories.fxml";
    private Path baseDirectory;
    
    public DestinationNamedDirectories(String[] fileInput) {
        baseDirectory = Paths.get(fileInput[1]);
    }
    
    public DestinationNamedDirectories(Path p) {
        baseDirectory = p;
    }
    
    public DestinationNamedDirectories(){
        baseDirectory = Paths.get("");
    }

    @Override
    public void moveFiles(List<FileMetaData> l) {
        for (FileMetaData file: l){
            Path seriesFolder = getBaseDirectory().resolve(file.getAttribute(FileAttributes.seriesName));
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
    public String getDescription() {
        return "put in Dir by Series Name";
    }
    
    @Override
    public String toString() {
        String s = "DestinationNamedDirectories,";
        s = s.concat(getBaseDirectory().toString());
        return s;
    }

    @Override
    public String getFXMLPath() {
        return fxmlPath;
    }

    /**
     * @return the baseDirectory
     */
    public Path getBaseDirectory() {
        return baseDirectory;
    }

    /**
     * @param baseDirectory the baseDirectory to set
     */
    public void setBaseDirectory(Path baseDirectory) {
        this.baseDirectory = baseDirectory;
    }
}
