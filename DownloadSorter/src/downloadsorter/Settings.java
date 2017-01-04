/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter;

import downloadsorter.model.FileOperation;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Eric
 */
public class Settings {
    private List<FileOperation> l;
    private ObservableList<FileOperation> filters;
    
    public Settings(SettingsManager s) {
        filters = FXCollections.observableArrayList(s.getSettings().getFilters());
    }
    
    public Settings(List<FileOperation> ls) {
        filters = FXCollections.observableArrayList(ls);
    }
    
    public Settings() {
        filters = FXCollections.observableArrayList(new ArrayList<>());
    }
    
    public ObservableList<FileOperation> getFilters() {
        return filters;
    }
    
    public void replaceFilter(int index, FileOperation filt) {
        if (index < filters.size() && index >= 0)
            filters.set(index, filt);
        else
            filters.add(filt);
    }
}
