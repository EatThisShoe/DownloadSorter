package downloadsorter;


import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eric
 */
public class FileName {
    public Path location;
    public String seriesName;
    public Boolean hasFansubFormat;
    
    FileName(Path p) {
        location = p;
        hasFansubFormat = false;
        seriesName = trimName();
    }
    
    public String trimName() {
        String s = location.toString();
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
}
