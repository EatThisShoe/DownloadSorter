/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.model;

import downloadsorter.view.rulepanes.DirectoryField;
import downloadsorter.view.rulepanes.FileField;
import downloadsorter.view.rulepanes.FlagField;
import downloadsorter.view.rulepanes.UIField;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;
import java.util.List;

/**
 *
 * @author Eric
 */
public class MoveAndList implements Rule, DestinationRule {
    private Path listLocation;
    private Path destination;
    private Boolean useAbsolutePaths;
    private Boolean copy;
    
    public MoveAndList(Path listLoc, Path dest, Boolean copyFiles, Boolean absolutePaths) {
        listLocation = listLoc;
        destination = dest;
        copy = copyFiles;
        useAbsolutePaths = absolutePaths;
    }
    
    public MoveAndList(String[] fromFile) {
        listLocation = Paths.get(fromFile[1]);
        destination = Paths.get(fromFile[2]);
        copy = Boolean.parseBoolean(fromFile[3]);
        useAbsolutePaths = Boolean.parseBoolean(fromFile[4]);
    }
    
    public MoveAndList() {
        listLocation = Paths.get("");
        destination = Paths.get("");
        useAbsolutePaths = false;
        copy = false;
        
    }
    
    @Override
    public String getDescription() {
        return "Move files and add them to a list";
    }

    @Override
    public void moveFiles(List<FileMetaData> l) {
        try {
            BufferedWriter listWriter = Files.newBufferedWriter(listLocation, CREATE, APPEND);
            for(FileMetaData file: l) {
                Path startLocation = file.getPath();
                Path fileName = startLocation.getFileName();

                try {
                    if (copy) {
                        Files.copy(startLocation, destination.resolve(fileName));
                    } else {
                        Files.move(startLocation, destination.resolve(fileName));
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                } finally {
                    String toWrite;
                    if(useAbsolutePaths) {
                        toWrite = startLocation.toString() + "\n";
                    } else {
                        toWrite = fileName.toString() + "\n";
                    }
                    listWriter.append(toWrite);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(
            MoveAndList.class.getCanonicalName() 
            + ",");
        s.append(listLocation.toString() + ",");
        s.append(destination.toString() + ",");
        s.append(copy.toString() + ",");
        s.append(useAbsolutePaths.toString() + ",");
        return s.toString();
        
    }

    /**
     * @return the listLocation
     */
    public Path getListLocation() {
        return listLocation;
    }

    /**
     * @return the destination
     */
    public Path getDestination() {
        return destination;
    }

    /**
     * @return the useAbsolutePaths
     */
    public Boolean getUseAbsolutePaths() {
        return useAbsolutePaths;
    }

    /**
     * @return the copy
     */
    public Boolean getCopy() {
        return copy;
    }

    @Override
    public UIField[] getFields() {
        UIField[] fields = new UIField[4];
        fields[0] = new FileField("List Location", listLocation);
        fields[1] = new DirectoryField("Destination", destination);
        fields[2] = new FlagField("Include", copy);
        fields[3] = new FlagField("Use AbsolutePaths", useAbsolutePaths);
        return fields;
    }
    
}
