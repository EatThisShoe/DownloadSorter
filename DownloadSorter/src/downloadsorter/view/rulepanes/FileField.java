/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.view.rulepanes;

import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class FileField implements UIField {
FlowPane fieldPane;
    Label fieldTitle;
    TextField txtLocation;
    Button browseButton;
    
    
    public FileField(String title, Path location) {
        fieldPane = new FlowPane();
        fieldTitle = new Label(title);
        txtLocation = new TextField(location.toString());
        browseButton = new Button();
        fieldPane.getChildren().add(fieldTitle);
        fieldPane.getChildren().add(txtLocation);
        fieldPane.getChildren().add(browseButton);
        browseButton.setOnAction(event -> {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Select the list file");
            Stage ownerWindow = new Stage();
            String filePath = "";
            filePath = chooser.showOpenDialog(ownerWindow).toString();
            txtLocation.setText(filePath);
        });
    }

    @Override
    public FlowPane getFlowPane() {
        return fieldPane;
    }

    @Override
    public Class getType() {
        return Path.class;
    }

    @Override
    public Object getInput() {
        return Paths.get(txtLocation.getText());
    }
}
