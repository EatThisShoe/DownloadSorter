/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter;

import downloadsorter.Factories.DestinationFactory;
import downloadsorter.Factories.FilterRuleFactory;
import downloadsorter.Factories.SourceFactory;
import downloadsorter.Filters.DestinationRule;
import downloadsorter.Filters.Filter;
import downloadsorter.Filters.FilterRule;
import downloadsorter.Filters.SourceRule;
import static downloadsorter.SettingsManager._settingsPath;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eric
 */
public class SettingsReader {
    Path settingsPath;
    List<String> lines;
    
    SettingsReader(Path p) {
        settingsPath = p;
        lines = new ArrayList<>();
    }
    
    public final Settings readSettingsFile() {
        List<Filter> filtersFromFile = new ArrayList<>();
        
        if (Files.exists(_settingsPath)) {
            try {
                lines = Files.readAllLines(_settingsPath);
                
                while (lines.size() > 2) {
                    String sourceLine = lines.get(0);
                    String filterLine = lines.get(1);
                    String destLine = lines.get(2);
                    SourceRule source = SourceFactory.createSourceRule(sourceLine);
                    FilterRule filt = FilterRuleFactory.createFilterRule(filterLine);
                    DestinationRule dest = DestinationFactory.createDestinationRule(destLine);
                    Filter f = new Filter(source, filt, dest);
                    filtersFromFile.add(f);
                    lines.remove(2);
                    lines.remove(1);
                    lines.remove(0);
                }

            } catch(Exception e) {System.err.format("IOException: %s%n", e);}
        } else { //defaults
            System.out.println("settings file not found");
        }
        
        return new Settings(filtersFromFile);
    }
}
