/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.view.rulepanes;

import downloadsorter.model.DirectorySource;
import downloadsorter.model.Rule;
import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Eric
 */
public class DirectorySourceController implements Initializable, RulePaneController {
    @FXML
    TextArea txtDirectories;
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
                StringBuilder oldText = new StringBuilder(txtDirectories.getText());
                if (oldText.charAt(oldText.length()-1) != '\n') {
                    oldText.append('\n');
                }
                oldText.append(filePath + "\n");
                txtDirectories.setText(oldText.toString());
            }
        });
    }    

    @Override
    public Rule getRule() {
        
        String[] dirs = txtDirectories.getText().split("\n");
        List<Path> sourceDirs = new ArrayList<>();
        for(String s: dirs) {
            sourceDirs.add(Paths.get(s));
        }
        return new DirectorySource(sourceDirs);
    }

    @Override
    public void setRule(Rule rule) {
        if(rule instanceof DirectorySource) {
            DirectorySource r = (DirectorySource) rule;
            StringBuilder listString = new StringBuilder();
            for (Path p : r.getSourceFolders()) {
                listString.append(p + "\n");
            }
            txtDirectories.setText(listString.toString());
        }
    }
    
}
