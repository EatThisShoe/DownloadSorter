/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.Filters;

import java.nio.file.Path;

/**
 *
 * @author Eric
 */
public class SourceFile implements FileMetaData {
    Path filePath;
    
    public SourceFile(Path p) {
        filePath = p;
    }

    @Override
    public Path getPath() {
        return filePath;
    }

    @Override
    public String getName() {
        return filePath.toString();
    }

    @Override
    public boolean isMatch() {
        return false;
    }
    
}
