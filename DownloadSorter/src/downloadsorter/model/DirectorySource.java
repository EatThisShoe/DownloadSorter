/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.model;

import downloadsorter.view.rulepanes.DirectoryListField;
import downloadsorter.view.rulepanes.UIField;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import static java.nio.file.FileVisitResult.CONTINUE;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eric
 */
public class DirectorySource implements SourceRule, Rule {
    List<Path> sourceFolders;

    public List<Path> getSourceFolders() {
        return sourceFolders;
    }

    public void setSourceFolders(List<Path> sourceFolders) {
        this.sourceFolders = sourceFolders;
    }
    
    public DirectorySource(String[] fromFile) {
        sourceFolders = new ArrayList<>();
        for ( int i = 1; i < fromFile.length; i++) {
            Path p = Paths.get(fromFile[i]);
            if (Files.exists(p))
                sourceFolders.add(p);
        }
    }
    
    public DirectorySource(Path p) {
        sourceFolders = new ArrayList<>();
        sourceFolders.add(p);
    }
    
    public DirectorySource(List<Path> l) {
        sourceFolders = new ArrayList<>();
        sourceFolders.addAll(l);
    }
    
    public DirectorySource() {
        sourceFolders = new ArrayList<>();
    }
  

    @Override
    public List<FileMetaData> getFiles() {
        List<FileMetaData> filesFound = new ArrayList<>();
        PathGatherer walker = new PathGatherer();
        
        sourceFolders.stream().forEach(dir -> {
            try {
                Files.walkFileTree(dir, walker);
                walker.getList().stream()
                        .forEach(sourceFile -> {if(Files.isReadable(sourceFile)) filesFound.add(new FileMetaData(sourceFile));});
            } catch(Exception e) {System.out.println(e.getMessage());}
        });
        return filesFound;
    }
    
    @Override 
    public String getDescription() {
        return "Get files from directory";
    }
    
    @Override
    public String toString() {
        String s = DirectorySource.class.getCanonicalName() + ",";
        for(Path p: sourceFolders) {
            s += p.toString() + ",";
        }
        return s;
    }

    @Override
    public UIField[] getFields() {
        UIField[] fields = new UIField[1];
        fields[0] = new DirectoryListField("Directories", sourceFolders);
        return fields;
    }
    
    
}

class PathGatherer extends SimpleFileVisitor<Path> {
    List<Path> found = new ArrayList<>();
    
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
        found.add(file);
        return CONTINUE;
    }
    
    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        System.out.println(exc.getMessage());
        return CONTINUE;
    }
    
    public List<Path> getList() {
        return found;
    }
}
