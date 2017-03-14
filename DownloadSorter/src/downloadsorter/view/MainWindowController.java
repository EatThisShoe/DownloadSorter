/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.view;

import downloadsorter.FXMain;
import downloadsorter.FileSorter;
import downloadsorter.model.FileOperation;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Eric
 */
public class MainWindowController implements Initializable {
    @FXML
    private ListView<FileOperation> operationList;
    @FXML
    private AnchorPane editorPane;
    @FXML
    private Button startButton;
    @FXML
    private Button addButton;
    @FXML
    private Button addSourceButton;
    @FXML
    private Button addFilterButton;
    @FXML
    private Button addDestButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button runButton;
    
    private VBox ruleEditor;
    private RuleEditorController editor;
    private FXMain mainApp;
    /**
     * Initializes the controller class.
     */
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showRuleEditor();
        operationList.setEditable(true);
        operationList.setCellFactory(TextFieldListCell.forListView(new StringConverter<FileOperation>() {
            @Override
            public FileOperation fromString(String s) {
                FileOperation fileOp = operationList.getItems().get(operationList.getEditingIndex());
                fileOp.setName(s);
                return fileOp;
            }
            @Override
            public String toString(FileOperation r) {
                return r.getName();
            }
        }));
        operationList.setOnEditCommit(editEvent -> {
             operationList.getItems().set(editEvent.getIndex(), editEvent.getNewValue());
        });
        operationList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    if(oldValue != null){
                        editor.saveOperation(oldValue);
                        writeOperations();
                    }
                    if(newValue != null) {
                        editor.setFileOperation(newValue);
                    }
                }
        );
        startButton.setOnAction(event -> {
            FileSorter sortingLoop = new FileSorter(operationList.getItems());
            Thread sortLoop = new Thread(sortingLoop);
            sortLoop.start();
            System.out.println("File Operations started.");
        });
        addButton.setOnAction((event) -> {
            operationList.getItems().add(new FileOperation());
            writeOperations();
        });
        deleteButton.setOnAction((event) -> {
            FileOperation toDelete = operationList.getSelectionModel().getSelectedItem();
            operationList.getItems().remove(toDelete);
            operationList.getSelectionModel().selectFirst();
            writeOperations();
        });
        addSourceButton.setOnAction((event) -> {
            editor.addNewSourceRule();
            writeOperations();
        });
        addFilterButton.setOnAction((event) -> {
            editor.addNewFilterRule();
            writeOperations();
        });
        addDestButton.setOnAction((event) -> {
            editor.addNewDestRule();
            writeOperations();
        });
        runButton.setOnAction(event -> {
            FileSorter operations = new FileSorter(operationList.getItems());
            Thread t = new Thread(operations);
            t.start();
        });
    }
    
    private void showRuleEditor() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXMain.class.getResource("view/RuleEditor.fxml"));
            ruleEditor = (VBox) loader.load();
            editor = loader.getController();
            editor.setMainWindow(this);
            editorPane.getChildren().add(ruleEditor);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setMainApp(FXMain main) {
        mainApp = main;
        ObservableList<FileOperation> l = mainApp.getFileOperations();
        operationList.setItems(l);
    }
    
    public void writeOperations() {
        mainApp.saveToDisk();
    }

    void updateSelectedOperation() {
        FileOperation fileOp = operationList.getSelectionModel().getSelectedItem();
        editor.saveOperation(fileOp);
        writeOperations();
    }
}
