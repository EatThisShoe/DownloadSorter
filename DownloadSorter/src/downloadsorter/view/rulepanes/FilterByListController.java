/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.view.rulepanes;

import downloadsorter.model.FilterByList;
import downloadsorter.model.FilterRule;
import downloadsorter.model.Rule;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Eric
 */
public class FilterByListController implements Initializable, RulePaneController {
    @FXML
    Button browseButton;
    @FXML
    CheckBox chkInclude;
    @FXML
    CheckBox chkAbsolutePaths;
    @FXML
    TextField txtLocation;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        chkInclude.setSelected(false);
        chkAbsolutePaths.setSelected(false);
        browseButton.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                FileChooser chooser = new FileChooser();
                chooser.setTitle("Select the list file");
                Stage ownerWindow = new Stage();
                String filePath = "";
                filePath = chooser.showOpenDialog(ownerWindow).toString();
                txtLocation.setText(filePath);
            }
        });
    }    

    @Override
    public Rule getRule() {
        String location = txtLocation.getText();
        Path loc = Paths.get(location);
        Boolean include = chkInclude.isSelected();
        Boolean absolutePaths = chkAbsolutePaths.isSelected();
        return new FilterByList(loc, include, absolutePaths);
    }

    @Override
    public void setRule(Rule rule) {
        FilterByList filterRule = (FilterByList) rule;
        txtLocation.setText(filterRule.getListLocation().toString());
        chkInclude.setSelected(filterRule.getInclude());
        chkAbsolutePaths.setSelected(filterRule.getUseAbsolutePaths());
    }
    
}
