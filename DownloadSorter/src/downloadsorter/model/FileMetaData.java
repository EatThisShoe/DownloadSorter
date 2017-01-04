/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.model;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Eric
 */
public class FileMetaData {
    private Path filePath;
    private EnumMap<FileAttributes, String> attributes;
    private Boolean dir;
    
    public FileMetaData(Path p) {
        filePath = p;
        dir = Files.isDirectory(p);
        attributes = new EnumMap(FileAttributes.class);
    }
    
    public Path getPath() {
        return filePath;
    }
    
    public String getAttribute(FileAttributes key) {
        return attributes.get(key);
    }
    
    public void addAttribute(FileAttributes key, String value) {
        attributes.put(key, value);
    }
}
