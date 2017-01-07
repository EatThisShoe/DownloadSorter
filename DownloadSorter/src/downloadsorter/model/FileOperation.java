/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.model;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Eric
 */
public class FileOperation {
    private ObservableList<SourceRule> sources;
    private ObservableList<FilterRule> filters;
    private ObservableList<DestinationRule> destinations;
    private String name;
    
    public FileOperation(List<SourceRule> s, List<FilterRule> f, List<DestinationRule> d, String name) {
        sources = FXCollections.observableArrayList(s);
        filters = FXCollections.observableArrayList(f);
        destinations = FXCollections.observableArrayList(d);
        this.name = name;
    }
    
    public FileOperation() {
        name = "new";
        sources = FXCollections.observableArrayList();
        filters = FXCollections.observableArrayList();
        destinations = FXCollections.observableArrayList();
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
                + getName() + "\n"
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
        return getName();
    }

    /**
     * @return the sources
     */
    public ObservableList<SourceRule> getSources() {
        return sources;
    }

    /**
     * @param sources the sources to set
     */
    public void setSources(ObservableList<SourceRule> sources) {
        this.sources = sources;
    }

    /**
     * @return the filters
     */
    public ObservableList<FilterRule> getFilters() {
        return filters;
    }

    /**
     * @param filters the filters to set
     */
    public void setFilters(ObservableList<FilterRule> filters) {
        this.filters = filters;
    }

    /**
     * @return the destinations
     */
    public ObservableList<DestinationRule> getDestinations() {
        return destinations;
    }

    /**
     * @param destinations the destinations to set
     */
    public void setDestinations(ObservableList<DestinationRule> destinations) {
        this.destinations = destinations;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    
}
