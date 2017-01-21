/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.view.rulepanes;

import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;


public class FlagField implements UIField {
    FlowPane fieldPane;
    Label fieldTitle;
    CheckBox checkBox;
    
    
    public FlagField(String title, Boolean isSet) {
        fieldPane = new FlowPane();
        fieldTitle = new Label(title);
        checkBox = new CheckBox();
        checkBox.setSelected(isSet);
        fieldPane.getChildren().add(fieldTitle);
        fieldPane.getChildren().add(checkBox);
    }

    @Override
    public FlowPane getFlowPane() {
        return fieldPane;
    }

    @Override
    public Class getType() {
        return Boolean.class;
    }

    @Override
    public Object getInput() {
        return checkBox.isSelected();
    }
    
}
