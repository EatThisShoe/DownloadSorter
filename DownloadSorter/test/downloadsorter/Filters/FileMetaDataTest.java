/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.Filters;

import java.nio.file.Path;
import java.nio.file.Paths;
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
public class FileMetaDataTest {
    
    public FileMetaDataTest() {
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
     * Test of getPath method, of class FileMetaData.
     */
//    @Test
//    public void testGetPath() {
//        System.out.println("getPath");
//        FileMetaData instance = null;
//        Path expResult = null;
//        Path result = instance.getPath();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getAttribute method, of class FileMetaData.
     */
    @Test
    public void testGetAttribute() {
        System.out.println("getAttribute");
        String key = "";
        Path p = Paths.get("/nothere");
        FileMetaData instance = new FileMetaData(p);
        instance.addAttribute("test", "test");
        String expResult = "test";
        String result = instance.getAttribute("test");
        assertEquals(expResult, result);
    }

    /**
     * Test of addAttribute method, of class FileMetaData.
//     */
//    @Test
//    public void testAddAttribute() {
//        System.out.println("addAttribute");
//        String key = "";
//        String value = "";
//        FileMetaData instance = null;
//        instance.addAttribute(key, value);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
