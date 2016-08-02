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
    public List<Filter> filters;
    
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
}
