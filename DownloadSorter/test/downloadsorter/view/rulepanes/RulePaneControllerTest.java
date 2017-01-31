/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.view.rulepanes;

import downloadsorter.FXMain;
import downloadsorter.model.DestinationNamedDirectories;
import downloadsorter.model.FansubFilter;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Eric
 */
public class RulePaneControllerTest {
    RulePaneController instance;
    FansubFilter rule;
    DestinationNamedDirectories rule2;
    Path apath;
    
    public RulePaneControllerTest() {
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
        apath = Paths.get("somepath");
        rule = new FansubFilter();
        rule2 = new DestinationNamedDirectories(apath);
    }

    @Test
    public void testGetPane() {
        System.out.println("getPane");
        instance = new RulePaneController(rule);
        assertNotNull(instance.getPane());
        
        instance = new RulePaneController(rule2);
        assertNotNull(instance.getPane());
    }

    @Test
    public void testGetRule() {
        System.out.println("getRule");
        instance = new RulePaneController(rule);
        Object result = instance.getRule();
        assertTrue(result.getClass() == rule.getClass());
        
        instance = new RulePaneController(rule2);
        DestinationNamedDirectories returnedRule = (DestinationNamedDirectories) instance.getRule();
        assertEquals(returnedRule.getBaseDirectory(), apath);
    }

    @Test
    public void testSetRule() {
        System.out.println("setRule");
        instance = new RulePaneController(rule);
        instance.setRule(rule2);
        DestinationNamedDirectories returnedRule = (DestinationNamedDirectories) instance.getRule();
        assertEquals(returnedRule.getBaseDirectory(), apath);
    }
    
}
