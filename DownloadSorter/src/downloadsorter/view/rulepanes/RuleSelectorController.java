/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.view.rulepanes;

import downloadsorter.FXMain;
import downloadsorter.model.DestinationNamedDirectories;
import downloadsorter.model.DestinationRule;
import downloadsorter.model.DirectorySource;
import downloadsorter.model.FansubFilter;
import downloadsorter.model.FilterRule;
import downloadsorter.model.Rule;
import downloadsorter.model.SourceRule;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import javax.swing.event.ChangeEvent;

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
    Button saveButton;
    @FXML
    VBox basePane;
    
    FlowPane rulePane;
    RuleEditorController parent;
    RulePaneController<T> ruleControl;
    /**
     * Initializes the controller class.
     */
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
        ruleSelector.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<T>() {
            @Override
            public void changed(ObservableValue<? extends T> observable, T oldValue, T newValue) {
                setRulePane(newValue);
            }
        });
        removeButton.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                parent.remove(basePane);
            }
            
        });
    }    
    
    public void setParentController(RuleEditorController parentControl) {
        parent = parentControl;
    }
    
    public void setComboBox(T rule) {
        ObservableList<T> dummyInstances = FXCollections.observableArrayList();
        RuleEnum[] ruleType = null;
        if (rule instanceof SourceRule) {
            ruleType = SourceType.values();
        } else if (rule instanceof FilterRule) {
            ruleType = FilterType.values();
        } else if (rule instanceof DestinationRule) {
            ruleType = DestinationType.values();
        }
        for (RuleEnum r : ruleType) {
            T dummy = (T)r.getDummyInstance();
            dummyInstances.add(dummy);
        }
        for (T r: dummyInstances) {
            if (r.getClass().equals(rule.getClass())){
                dummyInstances.set(dummyInstances.indexOf(r), rule);
            }
        }
        ruleSelector.setItems(dummyInstances);
        ruleSelector.getSelectionModel().select(rule);
    }
    
    public void setRulePane(T rule) {
        
        String location = rule.getFXMLPath();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXMain.class.getResource(location));
            basePane.getChildren().remove(rulePane);
            rulePane = (FlowPane) loader.load();
            basePane.getChildren().add(rulePane);
            ruleControl = loader.getController();
            ruleControl.setRule(rule);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public T getRule() {
        return ruleControl.getRule();
    }
    
    
interface RuleEnum {
    Rule getDummyInstance();
}
enum SourceType implements RuleEnum{
    DirectorySource;
    
    public SourceRule getDummyInstance() {
        switch (this) {
            case DirectorySource: return new DirectorySource();
            default: return null;
        }
    }
}
enum FilterType implements RuleEnum{
    FansubFilter;
    
    public FilterRule getDummyInstance() {
        switch (this) {
            case FansubFilter: return new FansubFilter();
            default: return null;
        }
    }
}
enum DestinationType implements RuleEnum{
    DestinationNamedDirectories;
    
    public DestinationRule getDummyInstance() {
        switch (this) {
            case DestinationNamedDirectories: return new DestinationNamedDirectories();
            default: return null;
        }
    }
}
}