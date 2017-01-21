/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.view.rulepanes;

import downloadsorter.model.Rule;
import java.lang.reflect.Constructor;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 *
 * @author Eric
 */
public class RulePaneController<T extends Rule> {
    VBox rulePane;
    Class ruleType;
    UIField[] fields;
    
    public RulePaneController(T rule) {
        rulePane = new VBox();
        ruleType = rule.getClass();
        setRule(rule);
    }
    
    public VBox getPane() {
        return rulePane;
    }
    
    public T getRule() {
        Class[] params = new Class[fields.length];
        for(int i = 0; i < fields.length; i++) {
            params[i] = fields[i].getType();
        }
        Object[] args = new Object[fields.length];
        for(int i = 0; i< fields.length; i++) {
            args[i] = fields[i].getInput();
        }
        T rule = null;
        try {
            Constructor con = ruleType.getConstructor(params);
            rule = (T) con.newInstance(args);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rule;
    }
     
    void setRule(T rule) {
        rulePane.getChildren().clear();
        ruleType = rule.getClass();
        fields = rule.getFields();
        for(UIField field : fields) {
            rulePane.getChildren().add(field.getFlowPane());
        }
    }
}
