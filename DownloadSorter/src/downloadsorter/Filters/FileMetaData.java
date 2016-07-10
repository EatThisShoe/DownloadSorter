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
public interface FileMetaData {
    public Path getPath();
    public String getName();
    public boolean isMatch();
}
