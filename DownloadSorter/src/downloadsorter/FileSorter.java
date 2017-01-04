/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter;

import downloadsorter.model.FileOperation;

/**
 *
 * @author Eric
 */
public class FileSorter implements Runnable {
    final Settings settings;
    
    public FileSorter(Settings init) {
        settings = init;
    }
    
    public void run() {
        for (FileOperation filter : settings.getFilters()) {
            filter.FilterFiles();
        }
    }
}
