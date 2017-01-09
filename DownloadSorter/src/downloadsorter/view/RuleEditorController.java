/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.view;

import downloadsorter.FXMain;
import downloadsorter.model.DestinationNamedDirectories;
import downloadsorter.model.DestinationRule;
import downloadsorter.model.DirectorySource;
import downloadsorter.model.FansubFilter;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sources = FXCollections.observableArrayList();
        filters = FXCollections.observableArrayList();
        destinations = FXCollections.observableArrayList();
    }    
    
    public void setFileOperation(FileOperation selectedOperation) {
        ruleList.getChildren().clear();
        sources.clear();
        filters.clear();
        destinations.clear();
        selectedOperation.getSources().forEach(rule -> sources.add(new RuleItem(rule)));
        selectedOperation.getFilters().forEach(rule -> filters.add(new RuleItem(rule)));
        selectedOperation.getDestinations().forEach(rule -> destinations.add(new RuleItem(rule)));
        sources.forEach(ruleItem -> ruleList.getChildren().add(ruleItem.pane));
        filters.forEach(ruleItem -> ruleList.getChildren().add(ruleItem.pane));
        destinations.forEach(ruleItem -> ruleList.getChildren().add(ruleItem.pane));
    }

    public void remove(VBox ruleSelector) {
        ruleList.getChildren().remove(ruleSelector);
        sources.removeIf( r-> r.pane == ruleSelector);
        filters.removeIf( r-> r.pane == ruleSelector);
        destinations.removeIf( r-> r.pane == ruleSelector);
    }

    public void saveOperation(FileOperation displayedOperation) {
            displayedOperation.setSources(saveRuleList(sources));
            displayedOperation.setFilters(saveRuleList(filters));
            displayedOperation.setDestinations(saveRuleList(destinations));
    }

    private <T extends Rule> ObservableList<T> saveRuleList( ObservableList<RuleItem<T>> ruleItemList) {
        ruleItemList.forEach((RuleItem r) -> r.rule = r.controller.saveRule());
        ObservableList<T> ruleList = FXCollections.observableArrayList();
        ruleItemList.forEach(r -> ruleList.add(r.rule));
        return ruleList;
    }

    public void addNewSourceRule() {
        DirectorySource newRule = new DirectorySource();
        RuleItem newItem = new RuleItem(newRule);
        sources.add(newItem);
        ruleList.getChildren().add(newItem.pane);
    }
    
    void addNewFilterRule() {
        FansubFilter newRule = new FansubFilter();
        RuleItem newItem = new RuleItem(newRule);
        filters.add(newItem);
        ruleList.getChildren().add(newItem.pane);
    }
    
    void addNewDestRule() {
        DestinationNamedDirectories newRule = new DestinationNamedDirectories();
        RuleItem newItem = new RuleItem(newRule);
        destinations.add(newItem);
        ruleList.getChildren().add(newItem.pane);
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


