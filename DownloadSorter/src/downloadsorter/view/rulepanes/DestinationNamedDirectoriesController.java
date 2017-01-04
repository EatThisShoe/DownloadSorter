/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.view.rulepanes;

import downloadsorter.model.DestinationNamedDirectories;
import downloadsorter.model.Rule;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Eric
 */
public class DestinationNamedDirectoriesController implements Initializable, RulePaneController {

    @FXML 
    TextField txtDirectory;
    @FXML
    Button browseButton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        browseButton.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                DirectoryChooser dirChooser = new DirectoryChooser();
                dirChooser.setTitle("Select base directory to move files");
                Stage ownerWindow = new Stage();
                String filePath = dirChooser.showDialog(ownerWindow).toString();
                txtDirectory.setText(filePath);
            }
        });
    }    

    @Override
    public Rule getRule() {
        return new DestinationNamedDirectories(Paths.get(txtDirectory.getText()));
    }

    @Override
    public void setRule(Rule r) {
        
        if (r instanceof DestinationNamedDirectories) {
            DestinationNamedDirectories rule = (DestinationNamedDirectories) r;
            txtDirectory.setText(rule.getBaseDirectory().toString());
        }
    }
    
}
