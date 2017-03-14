/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.view.rulepanes;

import downloadsorter.AllRules;
import downloadsorter.model.Rule;
import downloadsorter.view.RuleEditorController;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Eric
 */
public class RuleSelectorController implements Initializable {

    @FXML
    ChoiceBox<Rule> ruleSelector;
    @FXML
    Button removeButton;
    @FXML
    VBox basePane;
    
    VBox rulePane;
    RuleEditorController parent;
    RulePaneController ruleControl;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ruleSelector.setConverter(new StringConverter<Rule>() {
            @Override
            public Rule fromString(String s) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
            @Override
            public String toString(Rule r) {
                return r.getDescription();
            }
        });
        ruleSelector.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Rule> observable, Rule oldValue, Rule newValue) -> {
            setRulePane(newValue);
        });
        removeButton.setOnAction((event) -> {
            parent.remove(basePane);
        });
    }    
    
    public void setParentController(RuleEditorController parentControl) {
        parent = parentControl;
        
    }
    
    public void setComboBox(Rule rule) {
        List<Rule> allRules = AllRules.getInstance().getRules();
        ObservableList<Rule> dummyInstances = FXCollections.observableArrayList(allRules);
        for (Rule r: dummyInstances) {
            if (r.getClass().equals(rule.getClass())){
                dummyInstances.set(dummyInstances.indexOf(r), rule);
            }
        }
        ruleSelector.setItems(dummyInstances);
        ruleSelector.getSelectionModel().select(rule);
    }
    
    public void setRulePane(Rule rule) {
        ruleControl = new RulePaneController(rule);
        basePane.getChildren().remove(rulePane);
        rulePane = ruleControl.getPane();
        basePane.getChildren().add(rulePane);
    }

    public Rule saveRule() {
        int index = ruleSelector.getSelectionModel().getSelectedIndex();
        Rule rule = ruleControl.getRule();
        ruleSelector.getItems().set(index, rule);
        ruleSelector.getSelectionModel().select(rule);
        return rule;
    }
}