/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.Filters;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
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
public class FansubFilterTest {
    Path sampleDir;
    Path testDir;
    List<FileMetaData> input;
    
    public FansubFilterTest() {
        sampleDir = Paths.get("Filter test sample data", "sample source");
        testDir = Paths.get("Filter test sample data", "test source");
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        TestFolderCopier freshTestFolder = new TestFolderCopier(sampleDir, testDir);
        try {Files.walkFileTree(sampleDir, freshTestFolder);}
        catch(Exception exc) {System.out.println("Test Folder not copied cleanly: " + exc.getMessage());}
        
        DirectorySource inputGenerator = new DirectorySource(testDir);
        input = inputGenerator.getFiles();
    }
    
    @After
    public void tearDown() {
        TestCleaner cleaner = new TestCleaner();
        try {Files.walkFileTree(testDir, cleaner);}
        catch (Exception e) {System.out.println(e.getMessage());}
    }

    /**
     * Test of filterFiles method, of class FansubFilter.
     */
    @Test
    public void testFilterFiles() {
        System.out.println("filterFiles: all files have match flag set.");
        FansubFilter instance = new FansubFilter();
        List<FileMetaData> result = instance.filterFiles(input);
        boolean fansubFormat = result.stream().allMatch(file -> file.getAttribute("series name") != null);
        assertTrue(fansubFormat);
    }
    
    @Test
    public void testFilterFilesForDirectories() {
        System.out.println("filterFiles: returned files do not contain directories.");
        FansubFilter instance = new FansubFilter();
        List<FileMetaData> result = instance.filterFiles(input);
        boolean hasDirectories = result.stream().anyMatch(file -> Files.isDirectory(file.getPath()));
        assertFalse(hasDirectories);
    }
    
    @Test
    public void testFilterFilesForIntendedMatch() {
        System.out.println("filterFiles: 2 files match name Has Match.");
        FansubFilter instance = new FansubFilter();
        List<FileMetaData> result = instance.filterFiles(input);
        List<FileMetaData> matchingPair = result.stream()
                .filter(file -> file.getAttribute("series name").equals("Has Match"))
                .collect(Collectors.toList());
        assertEquals(2, matchingPair.size());
    }
    
    @Test
    public void testFilterFilesForIntendedMatch2() {
        System.out.println("filterFiles: 2 files match in sub directories.");
        FansubFilter instance = new FansubFilter();
        List<FileMetaData> result = instance.filterFiles(input);
        List<FileMetaData> matchingPair = result.stream()
                .filter(file -> file.getAttribute("series name").equals("Nested Match"))
                .collect(Collectors.toList());
        assertEquals(2, matchingPair.size());
    }
    
    @Test
    public void testFilterFilesForIntendedMatch3() {
        System.out.println("filterFiles: 2 files match across 2 separate directories.");
        FansubFilter instance = new FansubFilter();
        List<FileMetaData> result = instance.filterFiles(input);
        List<FileMetaData> matchingPair = result.stream()
                .filter(file -> file.getAttribute("series name").equals("Match in Other Dir"))
                .collect(Collectors.toList());
        assertEquals(2, matchingPair.size());
    }
}
