/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eric
 */
public class FileOperation {
    private List<SourceRule> sources;
    private List<FilterRule> filters;
    private List<DestinationRule> destinations;
    String name;
    
    public FileOperation(List<SourceRule> s, List<FilterRule> f, List<DestinationRule> d, String name) {
        sources = s;
        filters = f;
        destinations = d;
        this.name = name;
    }
    
    public void FilterFiles() {
        List files = new ArrayList();
        for(SourceRule s : getSources()){
            files.addAll(s.getFiles());
        }
        for(FilterRule f : getFilters()) {
            files = f.filterFiles(files);
        }
        for(DestinationRule d : getDestinations()) {
            d.moveFiles(files);
        }
    }
    
    public String stringForFile() {
        String srcStr = "";
        for(SourceRule src: getSources())
            srcStr += src.toString();
        String filtStr = "";
        for(FilterRule filt : getFilters())
            filtStr += filt.toString();
        String destStr = "";
        for(DestinationRule dest : getDestinations())
            destStr += dest.toString();
        return ("FILTER\n"
                + name + "\n"
                + "SOURCERULES\n"
                + srcStr + "\n"
                + "FILTERRULES\n"
                + filtStr + "\n"
                + "DESTINATIONRULES\n"
                + destStr + "\n"
                + "ENDFILTER\n");
    }
    
    public String[] getSourceParamaters() {
        return getSources().toString().split(",");
    }
    
    public String[] getFilterParameters() {
        return getFilters().toString().split(",");
    }
    
    public String[] getDestinationParameters() {
        return getDestinations().toString().split(",");
    }
    
    public String toString() {
        return name;
    }

    /**
     * @return the sources
     */
    public List<SourceRule> getSources() {
        return sources;
    }

    /**
     * @param sources the sources to set
     */
    public void setSources(List<SourceRule> sources) {
        this.sources = sources;
    }

    /**
     * @return the filters
     */
    public List<FilterRule> getFilters() {
        return filters;
    }

    /**
     * @param filters the filters to set
     */
    public void setFilters(List<FilterRule> filters) {
        this.filters = filters;
    }

    /**
     * @return the destinations
     */
    public List<DestinationRule> getDestinations() {
        return destinations;
    }

    /**
     * @param destinations the destinations to set
     */
    public void setDestinations(List<DestinationRule> destinations) {
        this.destinations = destinations;
    }
}
