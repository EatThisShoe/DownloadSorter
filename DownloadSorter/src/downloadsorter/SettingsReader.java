/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter;

import downloadsorter.model.FileOperation;
import downloadsorter.model.Rule;
import java.io.IOException;
import java.lang.reflect.Constructor;
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
    
    public static Rule createRule(String fileInput) {
        String[] args = fileInput.split(",", -1);
        try {
            Class cl = Class.forName(args[0]);
            Class[] parameters = new Class[1];
            parameters[0] = args.getClass();
            Constructor constructor = cl.getConstructor(parameters);
            Rule rule = (Rule) constructor.newInstance((Object) args);
            return rule;
        } catch (Exception e) {}
        return null;
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
                            Rule rule = createRule(line);
                            if(rule != null) {
                                rules.add(rule);
                            }
                        }
                        FileOperation f = new FileOperation(rules, name);
                        filtersFromFile.add(f);
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
