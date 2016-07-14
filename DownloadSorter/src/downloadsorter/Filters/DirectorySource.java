/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.Filters;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import static java.nio.file.FileVisitResult.CONTINUE;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eric
 */
public class DirectorySource implements SourceRule {
    List<Path> sourceFolders = new ArrayList<>();
    
    public DirectorySource(Path p) {
        sourceFolders.add(p);
    }
    
    public DirectorySource(Path p, Path p2) {
        sourceFolders.add(p);
        sourceFolders.add(p2);
    }
    
    public DirectorySource(List<Path> l) {
        sourceFolders = l;
    }

    @Override
    public List<Path> getFiles() {
        List<Path> filesFound = new ArrayList<>();
        PathGatherer walker = new PathGatherer();
        
        sourceFolders.stream().forEach(dir -> {
            try {
                Files.walkFileTree(dir, walker);
                walker.getList().stream()
                        .forEach(sourceFile -> {if(Files.isReadable(sourceFile)) filesFound.add(sourceFile);});
            } catch(Exception e) {System.out.println(e.getMessage());}
        });
        return filesFound;
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
