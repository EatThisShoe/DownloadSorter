/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.Filters;

import java.util.List;

/**
 *
 * @author Eric
 */
public class Filter {
    SourceRule source;
    FilterRule filter;
    DestinationRule destination;
    
    Filter(SourceRule s, FilterRule f, DestinationRule d) {
        source = s;
        filter = f;
        destination = d;
    }
    
    public void FilterFiles() {
        List files = source.getFiles();
        List relevant = filter.filterFiles(files);
        destination.moveFiles(relevant);
    }
}
