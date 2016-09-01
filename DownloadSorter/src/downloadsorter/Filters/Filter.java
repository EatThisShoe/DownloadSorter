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
    String name;
    
    public Filter(SourceRule s, FilterRule f, DestinationRule d, String name) {
        source = s;
        filter = f;
        destination = d;
        this.name = name;
    }
    
    public void FilterFiles() {
        List files = source.getFiles();
        List relevant = filter.filterFiles(files);
        destination.moveFiles(relevant);
    }
    
    public String stringForFile() {
        return (name + "\n"
                + source.toString() + "\n"
                + filter.toString() + "\n"
                + destination.toString() + "\n");
    }
    
    public String[] getSourceParamaters() {
        return source.toString().split(",");
    }
    
    public String[] getFilterParameters() {
        return filter.toString().split(",");
    }
    
    public String[] getDestinationParameters() {
        return destination.toString().split(",");
    }
    
    public String toString() {
        return name;
    }
}
