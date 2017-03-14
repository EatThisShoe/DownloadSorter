/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.model;

import downloadsorter.view.rulepanes.DirectoryListField;
import downloadsorter.view.rulepanes.FlagField;
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
public class DirectorySource implements Rule {
    List<Path> sourceFolders;
    Boolean searchSubDirs;
    
    public DirectorySource(String[] fromFile) {
        sourceFolders = new ArrayList<>();
        searchSubDirs = Boolean.parseBoolean(fromFile[1]);
        for ( int i = 2; i < fromFile.length; i++) {
            Path p = Paths.get(fromFile[i]);
            if (Files.exists(p))
                sourceFolders.add(p);
        }
    }
    
    public DirectorySource(Boolean searchSubs, List<Path> l) {
        searchSubDirs = searchSubs;
        sourceFolders = new ArrayList<>();
        sourceFolders.addAll(l);
    }
    
    public DirectorySource() {
        sourceFolders = new ArrayList<>();
        searchSubDirs = true;
    }
  

    @Override
    public List<FileMetaData> process(List<FileMetaData> previousFiles) {
        List<FileMetaData> filesFound = previousFiles;
        
        if (searchSubDirs) {
            sourceFolders.stream().forEach(dir -> {
                try {
                    PathGatherer walker = new PathGatherer();
                    Files.walkFileTree(dir, walker);
                    walker.getList().stream()
                            .forEach(sourceFile -> {if(Files.isReadable(sourceFile)) filesFound.add(new FileMetaData(sourceFile));});
                } catch(Exception e) {System.out.println(e.getMessage());}
            });
        } else {
            sourceFolders.stream().forEach(dir -> {
                try {
                    Files.list(dir).forEach(sourceFile -> {if(Files.isReadable(sourceFile)) filesFound.add(new FileMetaData(sourceFile));});
                } catch (Exception e) {
                    System.out.println("Unable to read files  in DirectorySource class\n");
                    e.printStackTrace();
                }
            });
        }
        return filesFound;
    }
    
    @Override 
    public String getDescription() {
        return "Get files from directory";
    }
    
    @Override
    public String toString() {
        String s = DirectorySource.class.getCanonicalName() + ",";
        s += searchSubDirs + ",";
        for(Path p: sourceFolders) {
            s += p.toString() + ",";
        }
        return s;
    }

    @Override
    public UIField[] getFields() {
        UIField[] fields = new UIField[2];
        fields[0] = new FlagField("Search subdirectories", searchSubDirs);
        fields[1] = new DirectoryListField("Directories", sourceFolders);
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
