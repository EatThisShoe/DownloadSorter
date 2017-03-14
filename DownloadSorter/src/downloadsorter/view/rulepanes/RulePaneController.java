/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.view.rulepanes;

import downloadsorter.model.Rule;
import java.lang.reflect.Constructor;
import javafx.scene.layout.VBox;

/**
 *
 * @author Eric
 */
public class RulePaneController {
    VBox rulePane;
    Class ruleType;
    UIField[] fields;
    
    public RulePaneController(Rule rule) {
        rulePane = new VBox();
        ruleType = rule.getClass();
        setRule(rule);
    }
    
    public VBox getPane() {
        return rulePane;
    }
    
    public Rule getRule() {
        Class[] params = new Class[fields.length];
        for(int i = 0; i < fields.length; i++) {
            params[i] = fields[i].getType();
        }
        Object[] args = new Object[fields.length];
        for(int i = 0; i< fields.length; i++) {
            args[i] = fields[i].getInput();
        }
        Rule rule = null;
        if (fields.length > 0) {
            try {
                Constructor con = ruleType.getConstructor(params);
                rule = (Rule) con.newInstance(args);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                rule = (Rule) ruleType.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rule;
    }
     
    void setRule(Rule rule) {
        rulePane.getChildren().clear();
        ruleType = rule.getClass();
        fields = rule.getFields();
        for(UIField field : fields) {
            rulePane.getChildren().add(field.getFlowPane());
        }
    }
}
