/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.Filters;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
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
        
        sourceFolders.stream().forEach(dir -> {
            try (DirectoryStream<Path> unsortedFiles = Files.newDirectoryStream(dir)){
                unsortedFiles.forEach(p -> {if(Files.isReadable(p)) filesFound.add(p);});
            } catch(Exception e) {System.out.println(e.getMessage());}
        });
        return filesFound;
    }
    
}
