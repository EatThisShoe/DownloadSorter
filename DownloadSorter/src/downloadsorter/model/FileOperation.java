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
    private ObservableList<Rule> rules;
    private String name;
    
    public FileOperation(List<Rule> rules, String name) {
        this.rules = FXCollections.observableArrayList(rules);
        this.name = name;
    }
    
    public FileOperation() {
        name = "new";
        rules = FXCollections.observableArrayList();
    }
    
    public void FilterFiles() {
        List files = new ArrayList();
        for(Rule r : rules){
            files = r.process(files);
        }
    }
    
    public String stringForFile() {
        String str = "";
        for(Rule r: rules)
            str += r.toString() + "\n";
        return ("FILTER\n"
                + getName() + "\n"
                + "RULES\n"
                + str
                + "ENDFILTER\n");
    }
    
    public String toString() {
        return getName();
    }

    /**
     * @return the sources
     */
    public ObservableList<Rule> getRules() {
        return rules;
    }

    /**
     * @param sources the sources to set
     */
    public void setRules(ObservableList<Rule> rules) {
        this.rules = rules;
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
