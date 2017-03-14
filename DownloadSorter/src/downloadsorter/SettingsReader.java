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
import downloadsorter.model.Rule;
import downloadsorter.model.SourceRule;
import java.io.IOException;
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
                        List<Rule> rules = new ArrayList();
                        
                        String name = itr.next();
                        while (itr.hasNext() && !line.equals("ENDFILTER")) {
                            line = itr.next();
                            
                            Rule source = SourceFactory.createSourceRule(line);
                            if (source != null)
                                rules.add(source);
                            else {
                                Rule filter = FilterRuleFactory.createFilterRule(line);
                                if (filter != null) {
                                    rules.add(filter);
                                } else {
                                    Rule dest = DestinationFactory.createDestinationRule(line);
                                    if (dest != null)
                                        rules.add(dest);
                                }
                            }
                        }
                        //if (!(sources.isEmpty() || filters.isEmpty() || destinations.isEmpty())) {
                            FileOperation f = new FileOperation(rules, name);
                            filtersFromFile.add(f);
                        //}
                    }
                }
            } catch(IOException e) {
                System.err.format("IOException: %s%n", e);
                e.printStackTrace();
            }
        } else { //defaults
            System.out.println("settings file not found");
        }
        
        return filtersFromFile;
    }
}
