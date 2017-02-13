/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.view.rulepanes;

import downloadsorter.FXMain;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;


public class DirectoryField implements UIField {
    FlowPane fieldPane;
    Label fieldTitle;
    TextField txtLocation;
    Button browseButton;
    
    
    public DirectoryField(String title, Path location) {
        try {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlfile = getClass().getResource("DirectoryField.fxml");
            loader.setLocation(fxmlfile);
            fieldPane = (FlowPane) loader.load();
            fieldTitle = (Label) loader.getNamespace().get("fieldTitle");
            txtLocation = (TextField) loader.getNamespace().get("txtDirectory");
            browseButton = (Button) loader.getNamespace().get("browseButton");
            
            fieldTitle.setText(title);
            txtLocation.setText(location.toString());
            browseButton.setOnAction(event -> {
                DirectoryChooser dirChooser = new DirectoryChooser();
                dirChooser.setTitle("Select base directory to move files");
                Stage ownerWindow = new Stage();
                final String filePath = dirChooser.showDialog(ownerWindow).toString();
                if(filePath != null) {
                    txtLocation.setText(filePath);
                }
            });
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
