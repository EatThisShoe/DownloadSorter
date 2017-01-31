/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.view.rulepanes;

import java.nio.file.Paths;
import javafx.application.Application;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Eric
 */
public class DirectoryFieldTest {
    DirectoryField instance;
    
    public DirectoryFieldTest() {
    }
    
    public static class AsNonApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // noop
    }
}

    @BeforeClass
    public static void initJFX() {
        Thread t = new Thread("JavaFX Init Thread") {
            public void run() {
                Application.launch(AsNonApp.class, new String[0]);
            }
        };
        t.setDaemon(true);
        t.start();
    }
    
    @Before
    public void setUp() {
        instance = new DirectoryField("Title", Paths.get("somepath"));
    }

    @Test
    public void testGetFlowPane() {
        System.out.println("getFlowPane");
        FlowPane result = null;
        result = instance.getFlowPane();
        assertNotNull(result);
    }

    @Rule
    public ExpectedException castFailed = ExpectedException.none();
    @Test
    public void testGetInput() {
        System.out.println("getInput");
        Object o = instance.getInput();
        assertNotNull(o);
        Class c = instance.getClass();
        castFailed.expect(ClassCastException.class);
        c.cast(o);
    }
    
}
