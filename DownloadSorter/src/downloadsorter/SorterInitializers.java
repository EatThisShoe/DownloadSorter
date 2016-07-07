/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Eric
 */
public class SorterInitializers {
    public final Path sourceFolder;
    
    public SorterInitializers(Settings s) {
        sourceFolder = s.getSourceFolder();
    }
}
