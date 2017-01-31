/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.view.rulepanes;

import downloadsorter.FXMain;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;


public class FlagField implements UIField {
    FlowPane fieldPane;
    CheckBox checkBox;
    
    
    public FlagField(String title, Boolean isSet) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXMain.class.getResource("view/rulepanes/FlagField.fxml"));
            fieldPane = (FlowPane) loader.load();
            checkBox = (CheckBox) loader.getNamespace().get("checkBox");

            checkBox.setText(title);
            checkBox.setSelected(isSet);
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
        return Boolean.class;
    }

    @Override
    public Object getInput() {
        return checkBox.isSelected();
    }
    
}
