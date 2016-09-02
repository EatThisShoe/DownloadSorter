/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter;

import downloadsorter.Filters.Filter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eric
 */
public class Settings {
    private List<Filter> filters;
    
    public Settings(SettingsManager s) {
        filters = s.getSettings().getFilters();
    }
    
    public Settings(List<Filter> ls) {
        filters = ls;
    }
    
    public Settings() {
        filters = new ArrayList<>();
    }
    
    public List<Filter> getFilters() {
        return filters;
    }
    
    public void replaceFilter(int index, Filter filt) {
        if (index < filters.size() && index >= 0)
            filters.set(index, filt);
        else
            filters.add(filt);
    }
}
