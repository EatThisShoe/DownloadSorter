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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
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
public class RuleSelectorController<T extends Rule> implements Initializable {

    @FXML
    ChoiceBox<T> ruleSelector;
    @FXML
    Button removeButton;
    @FXML
    VBox basePane;
    
    VBox rulePane;
    RuleEditorController parent;
    RulePaneController<T> ruleControl;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ruleSelector.setConverter(new StringConverter<T>() {
            @Override
            public T fromString(String s) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
            @Override
            public String toString(T r) {
                return r.getDescription();
            }
        });
        ruleSelector.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends T> observable, T oldValue, T newValue) -> {
            setRulePane(newValue);
        });
        removeButton.setOnAction((event) -> {
            parent.remove(basePane);
        });
    }    
    
    public void setParentController(RuleEditorController parentControl) {
        parent = parentControl;
        
    }
    
    public void setComboBox(T rule) {
        List<T> allRules = AllRules.getInstance().getRules(rule);
        ObservableList<T> dummyInstances = FXCollections.observableArrayList(allRules);
        for (T r: dummyInstances) {
            if (r.getClass().equals(rule.getClass())){
                dummyInstances.set(dummyInstances.indexOf(r), rule);
            }
        }
        ruleSelector.setItems(dummyInstances);
        ruleSelector.getSelectionModel().select(rule);
    }
    
    public void setRulePane(T rule) {
        ruleControl = new RulePaneController(rule);
        basePane.getChildren().remove(rulePane);
        rulePane = ruleControl.getPane();
        basePane.getChildren().add(rulePane);
    }

    public T saveRule() {
        int index = ruleSelector.getSelectionModel().getSelectedIndex();
        T rule = ruleControl.getRule();
        ruleSelector.getItems().set(index, rule);
        ruleSelector.getSelectionModel().select(rule);
        return rule;
    }
}