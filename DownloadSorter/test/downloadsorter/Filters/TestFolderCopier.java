/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.Filters;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import static java.nio.file.FileVisitResult.CONTINUE;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 *
 * @author Eric
 */
public class TestFolderCopier extends SimpleFileVisitor<Path> {
    Path source;
    Path target;
    
    TestFolderCopier(Path source, Path target) {
        this.source = source;
        this.target = target;
    }
    
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attr) {
        Path newdir = target.resolve(source.relativize(dir));
        try {Files.copy(dir, newdir);}
        catch(Exception e) {System.out.println(e.getMessage());}
        return CONTINUE;
    }
    
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
        Path newfile = target.resolve(source.relativize(file));
        try {Files.copy(file, newfile);}
        catch(Exception e) {System.out.println(e.getMessage());}
        return CONTINUE;
    }
    
    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        System.out.println(exc.getMessage());
        return CONTINUE;
    }
}