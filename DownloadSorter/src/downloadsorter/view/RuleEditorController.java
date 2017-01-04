/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.view;

import downloadsorter.FXMain;
import downloadsorter.model.DestinationRule;
import downloadsorter.model.FileOperation;
import downloadsorter.model.FilterRule;
import downloadsorter.model.Rule;
import downloadsorter.model.SourceRule;
import downloadsorter.view.rulepanes.RuleSelectorController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Eric
 */
public class RuleEditorController implements Initializable {
    @FXML
    private VBox ruleList;
    private ObservableList<RuleItem<SourceRule>> sources;
    private ObservableList<RuleItem<FilterRule>> filters;
    private ObservableList<RuleItem<DestinationRule>> destinations;
    private FileOperation displayedOperation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sources = FXCollections.observableArrayList();
        filters = FXCollections.observableArrayList();
        destinations = FXCollections.observableArrayList();
    }    
    
    public void setFilter(FileOperation selectedOperation) {
        displayedOperation = selectedOperation;
        sources.clear();
        filters.clear();
        destinations.clear();
        selectedOperation.getSources().forEach(rule -> sources.add(new RuleItem(rule)));
        selectedOperation.getFilters().forEach(rule -> filters.add(new RuleItem(rule)));
        selectedOperation.getDestinations().forEach(rule -> destinations.add(new RuleItem(rule)));
        
        for (RuleItem ri: sources){
              ruleList.getChildren().add(ri.pane);
        }
        for (RuleItem ri: filters){
              ruleList.getChildren().add(ri.pane);
        }
        for (RuleItem ri: destinations){
              ruleList.getChildren().add(ri.pane);
        }
    }

    public void remove(VBox ruleSelector) {
        ruleList.getChildren().remove(ruleSelector);
        for (RuleItem r : sources) {
            if (r.pane == ruleSelector) {
                sources.remove(r);
                break;
            }
        }
        for (RuleItem r : filters) {
            if (r.pane == ruleSelector) {
                filters.remove(r);
                break;
            }
        }
        for (RuleItem r : destinations) {
            if (r.pane == ruleSelector) {
                destinations.remove(r);
                break;
            }
        }
    }
    
    class RuleItem<T extends Rule> {
        public T rule;
        public VBox pane;
        public RuleSelectorController controller;
        RuleItem(T rule) {
            this.rule = rule;
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(FXMain.class.getResource("view/rulepanes/RuleSelector.fxml"));
                pane = (VBox) loader.load();
                controller = loader.getController();
                controller.setParentController(RuleEditorController.this);
                controller.setComboBox(rule);
                controller.setRulePane(rule);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


