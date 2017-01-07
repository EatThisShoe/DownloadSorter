/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter;

import downloadsorter.Factories.DestinationFactory;
import downloadsorter.Factories.FilterRuleFactory;
import downloadsorter.Factories.SourceFactory;
import downloadsorter.model.DestinationRule;
import downloadsorter.model.FileOperation;
import downloadsorter.model.FilterRule;
import downloadsorter.model.SourceRule;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    
    public final ObservableList<FileOperation> readSettingsFile() {
        ObservableList<FileOperation> filtersFromFile = FXCollections.observableArrayList();
        
        if (Files.exists(settingsPath)) {
            try {
                lines = Files.readAllLines(settingsPath);
                
                Iterator<String> itr = lines.iterator();
                while (itr.hasNext()) {
                    String line = itr.next();
                    if(line.equals("FILTER")) {
                        List<SourceRule> sources = new ArrayList();
                        List<FilterRule> filters = new ArrayList();
                        List<DestinationRule> destinations = new ArrayList();
                        
                        String name = itr.next();
                        while (itr.hasNext() && !line.equals("ENDFILTER")) {
                            line = itr.next();
                            
                            SourceRule source = SourceFactory.createSourceRule(line);
                            if (source != null)
                                sources.add(source);
                            else {
                                FilterRule filter = FilterRuleFactory.createFilterRule(line);
                                if (filter != null) {
                                    filters.add(filter);
                                } else {
                                    DestinationRule dest = DestinationFactory.createDestinationRule(line);
                                    if (dest != null)
                                        destinations.add(dest);
                                }
                            }
                        }
                        if (!(sources.isEmpty() || filters.isEmpty() || destinations.isEmpty())) {
                            FileOperation f = new FileOperation(sources, filters, destinations, name);
                            filtersFromFile.add(f);
                        }
                    }
                }
            } catch(Exception e) {System.err.format("IOException: %s%n", e);}
        } else { //defaults
            System.out.println("settings file not found");
        }
        
        return filtersFromFile;
    }
}
