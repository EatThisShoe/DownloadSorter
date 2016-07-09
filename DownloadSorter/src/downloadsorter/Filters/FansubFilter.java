/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.Filters;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 *
 * @author Eric
 */
public class FansubFilter implements FilterRule {

    @Override
    public List<Path> filterFiles(List<Path> l) {
        List<FileName> fansubs = l.stream()
                    .map(FileName::new)
                    .collect(Collectors.toList());
        
        return fansubs.stream()
                    .filter(f -> findSameSeries(f, fansubs))
                    .filter(f -> f.hasFansubFormat) //should remove directories after counting them for duplicates
                    .map(f -> f.location)
                    .collect(Collectors.toList());
    }
    
    boolean findSameSeries(FileName f, List<FileName> lf) {
        return lf.stream().anyMatch(a -> a.seriesName.equals(f.seriesName) && a.location != f.location);
    }
}

class FileName {
    public Path location;
    public String seriesName;
    public Boolean hasFansubFormat;
    
    FileName(Path p) {
        location = p;
        hasFansubFormat = false;
        seriesName = trimName();
    }
    
    public final String trimName() {
        String s = getLocation().toString();
        Pattern p = Pattern.compile("\\[.*\\] (.*) -.*");
        Matcher m = p.matcher(s);
        Boolean b = m.find();
        
        if (b) {
            hasFansubFormat = true;
            return m.group(1).trim();
        }
        else
            return s;
    }
    
    public String toString() {
        return seriesName;
    }

    /**
     * @return the location
     */
    public Path getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Path location) {
        this.location = location;
    }
}