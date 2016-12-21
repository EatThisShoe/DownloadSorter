/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.Filters;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Eric
 */
public class FileMetaData {
    private Path filePath;
    private Map<String, String> attributes;
    
    public FileMetaData(Path p) {
        filePath = p;
        attributes = new HashMap();
    }
    
    public Path getPath() {
        return filePath;
    }
    
    public String getAttribute(String key) {
        return attributes.get(key);
    }
    
    public void addAttribute(String key, String value) {
        attributes.put(key, value);
    }
}
