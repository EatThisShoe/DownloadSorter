/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.view.rulepanes;

import downloadsorter.FXMain;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.fxml.FXMLLoader;
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
    Button browseButton, createButton;
    
    
    public FileField(String title, Path location) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXMain.class.getResource("view/rulepanes/FileField.fxml"));
            fieldPane = (FlowPane) loader.load();
            fieldTitle = (Label) loader.getNamespace().get("fieldTitle");
            txtLocation = (TextField) loader.getNamespace().get("txtDirectory");
            browseButton = (Button) loader.getNamespace().get("browseButton");
            createButton = (Button) loader.getNamespace().get("createButton");
            
            fieldTitle.setText(title);
            txtLocation.setText(location.toString());
            browseButton.setOnAction(event -> {
                FileChooser chooser = new FileChooser();
                chooser.setTitle("Select the list file");
                Stage ownerWindow = new Stage();
                String filePath = "";
                filePath = chooser.showSaveDialog(ownerWindow).toString();
                txtLocation.setText(filePath);
            });
//            createButton.setOnAction(event -> {
//                FileChooser chooser = new FileChooser();
//                chooser.setTitle("Select the list file");
//                Stage ownerWindow = new Stage();
//                String filePath = "";
//                filePath = chooser.showSaveDialog(ownerWindow).toString();
//                txtLocation.setText(filePath);
//            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        
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
