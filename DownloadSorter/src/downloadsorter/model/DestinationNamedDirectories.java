/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.model;

import downloadsorter.view.rulepanes.DirectoryField;
import downloadsorter.view.rulepanes.UIField;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eric
 */
public class DestinationNamedDirectories implements Rule {
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
    public List<FileMetaData> process(List<FileMetaData> l) {
        List<FileMetaData> successful = new ArrayList<>();
        for (FileMetaData file: l){
            Path seriesFolder = baseDirectory.resolve(file.getAttribute(FileAttributes.seriesName));
            if(!Files.exists(seriesFolder)) {
                try {Files.createDirectory(seriesFolder);}
                catch(Exception e) {System.out.println("Error creating directory: " + e.getMessage());}
            }
            Path inDir = seriesFolder.resolve(file.getPath().getFileName());
            try {
                Files.move(file.getPath(), inDir);
                successful.add(file);
            }
            catch(Exception e) {System.out.println("Error moving file: " + e.getMessage());}
        }
        return successful;
    }
    
    @Override
    public String getDescription() {
        return "put in Dir by Series Name";
    }
    
    @Override
    public String toString() {
        String s = DestinationNamedDirectories.class.getCanonicalName() + ",";
        s = s.concat(getBaseDirectory().toString() + ",");
        return s;
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

    @Override
    public UIField[] getFields() {
        UIField[] fields = new UIField[1];
        fields[0] = new DirectoryField("Base Directory", baseDirectory);
        return fields;
    }
}
