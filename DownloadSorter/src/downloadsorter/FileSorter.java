/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter;

import downloadsorter.model.FileOperation;
import java.util.List;

/**
 *
 * @author Eric
 */
public class FileSorter implements Runnable {
    final List<FileOperation> active;
    
    public FileSorter(List<FileOperation> active) {
        this.active = active;
    }
    
    public void run() {
        for (FileOperation filter : active) {
            filter.FilterFiles();
        }
    }
}
