/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.view;

import downloadsorter.FXMain;
import downloadsorter.model.FileOperation;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Eric
 */
public class MainWindowController implements Initializable {
    @FXML
    private ListView<FileOperation> filterList;
    @FXML
    private AnchorPane editorPane;
    
    private VBox ruleEditor;
    private RuleEditorController editor;
    private FXMain mainApp;
    private FileOperation selectedFilter;
    /**
     * Initializes the controller class.
     */
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showRuleEditor();
        filterList.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
//                    displayFilter(newValue);
                    selectedFilter = newValue;
                    editor.setFilter(selectedFilter);
                }
        );
    }
    
    private void showRuleEditor() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXMain.class.getResource("view/RuleEditor.fxml"));
            ruleEditor = (VBox) loader.load();
            editor = loader.getController();
            editorPane.getChildren().add(ruleEditor);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setMainApp(FXMain main) {
        mainApp = main;
        ObservableList<FileOperation> l = mainApp.getFiltersList();
        filterList.setItems(l);
    }
}
