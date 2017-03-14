/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.view;

import downloadsorter.FXMain;
import downloadsorter.model.DestinationNamedDirectories;
import downloadsorter.model.DirectorySource;
import downloadsorter.model.FansubFilter;
import downloadsorter.model.FileOperation;
import downloadsorter.model.Rule;
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
    private ObservableList<RuleItem> rules;
    private MainWindowController mainWindow;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rules = FXCollections.observableArrayList();
    }    
    
    public void setFileOperation(FileOperation selectedOperation) {
        ruleList.getChildren().clear();
        rules.clear();
        selectedOperation.getRules().forEach(rule -> rules.add(new RuleItem(rule)));
        rules.forEach(ruleItem -> ruleList.getChildren().add(ruleItem.pane));
    }

    public void remove(VBox ruleSelector) {
        ruleList.getChildren().remove(ruleSelector);
        rules.removeIf( r-> r.pane == ruleSelector);
        mainWindow.updateSelectedOperation();
    }

    public void saveOperation(FileOperation displayedOperation) {
            displayedOperation.setRules(saveRuleList(rules));
    }

    private ObservableList<Rule> saveRuleList( ObservableList<RuleItem> ruleItemList) {
        ruleItemList.forEach((RuleItem r) -> r.rule = r.controller.saveRule());
        ObservableList<Rule> ruleList = FXCollections.observableArrayList();
        ruleItemList.forEach(r -> ruleList.add(r.rule));
        return ruleList;
    }

    public void addNewRule() {
        DirectorySource newRule = new DirectorySource();
        RuleItem newItem = new RuleItem(newRule);
        rules.add(newItem);
        ruleList.getChildren().add(newItem.pane);
    }

    void setMainWindow(MainWindowController main) {
        mainWindow = main;
    }
    
    class RuleItem {
        public Rule rule;
        public VBox pane;
        public RuleSelectorController controller;
        RuleItem(Rule rule) {
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


