/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter;

import downloadsorter.model.FileOperation;
import downloadsorter.view.MainWindowController;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Eric
 */
public class FXMain extends Application {
    BorderPane GUI;
    SettingsManager settings;
    Stage primaryStage;
    
    @Override
    public void start(Stage pStage) {
        this.primaryStage = pStage;
        settings = new SettingsManager();
        
        initRootLayout();
        showMainWindow();
        
        Scene scene = new Scene(GUI);
        primaryStage.setTitle("File Sorter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXMain.class.getResource("view/RootLayout.fxml"));
            GUI = (BorderPane) loader.load();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private void showMainWindow() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXMain.class.getResource("view/MainWindow.fxml"));
            AnchorPane mainWindow = (AnchorPane) loader.load();
            GUI.setCenter(mainWindow);
            MainWindowController controller = loader.getController();
            controller.setMainApp(this);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public ObservableList<FileOperation> getFileOperations() {
        return settings.getSettings();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
