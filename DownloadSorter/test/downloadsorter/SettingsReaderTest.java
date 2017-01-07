/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter;

import downloadsorter.model.FileOperation;
import java.nio.file.Paths;
import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Eric
 */
public class SettingsReaderTest {
    
    public SettingsReaderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of readSettingsFile method, of class SettingsReader.
     */
    @Test
    public void testReadSettingsFile() {
        System.out.println("readSettingsFile: Bad path returns empty list");
        SettingsReader instance = new SettingsReader(Paths.get("notarealpath"));
        ObservableList<FileOperation> set = instance.readSettingsFile();
        int result = set.size();
        System.out.println(result);
        assertEquals(result, 0);
    }
    
}
