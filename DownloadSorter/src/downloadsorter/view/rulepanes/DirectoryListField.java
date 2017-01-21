/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.view.rulepanes;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;


public class DirectoryListField implements UIField {
    FlowPane fieldPane;
    Label fieldTitle;
    TextArea txtDirectories;
    Button browseButton;
    
    
    public DirectoryListField(String title, List<Path> locations) {
        fieldPane = new FlowPane();
        fieldTitle = new Label(title);
        StringBuilder locString = new StringBuilder("");
        locations.forEach(path -> locString.append(path.toString() + "\n"));
        txtDirectories = new TextArea(locString.toString());
        browseButton = new Button();
        fieldPane.getChildren().add(fieldTitle);
        fieldPane.getChildren().add(txtDirectories);
        fieldPane.getChildren().add(browseButton);
        browseButton.setOnAction(event -> {
                DirectoryChooser dirChooser = new DirectoryChooser();
                dirChooser.setTitle("Select base directory to move files");
                Stage ownerWindow = new Stage();
                String filePath = dirChooser.showDialog(ownerWindow).toString();
                StringBuilder oldText = new StringBuilder(txtDirectories.getText());
                if (oldText.length() > 0 && oldText.charAt(oldText.length()-1) != '\n') {
                    oldText.append('\n');
                }
                oldText.append(filePath + "\n");
                txtDirectories.setText(oldText.toString());
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
        String[] dirs = txtDirectories.getText().split("\n");
        List<Path> sourceDirs = new ArrayList<>();
        for(String s: dirs) {
            sourceDirs.add(Paths.get(s));
        }
        return dirs;
    }
    
}
