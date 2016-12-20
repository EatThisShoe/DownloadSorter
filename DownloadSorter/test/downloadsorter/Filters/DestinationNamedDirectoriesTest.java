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
public class DestinationNamedDirectoriesTest {
    Path sampleDir;
    Path testDir;
    Path destDir;
    List<FileMetaData> input;
    
    public DestinationNamedDirectoriesTest() {
        sampleDir = Paths.get("Filter test sample data", "sample source");
        testDir = Paths.get("Filter test sample data", "test source");
        destDir = Paths.get("Filter test sample data", "test destination");
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
        
        try {Files.createDirectory(destDir);}
        catch(Exception exc) {System.out.println("Destination directory not created: " + exc.getMessage());}
        
        SourceRule sourceReader = new DirectorySource(testDir);
        FilterRule readerFilter = new FansubFilter();
        input = readerFilter.filterFiles(sourceReader.getFiles());
    }
    
    @After
    public void tearDown() {
        TestCleaner cleaner = new TestCleaner();
        try {Files.walkFileTree(testDir, cleaner);}
        catch (Exception e) {System.out.println(e.getMessage());}
        
        try {Files.walkFileTree(destDir, cleaner);}
        catch (Exception e) {System.out.println(e.getMessage());}
    }

    /**
     * Test of moveFiles method, of class DestinationNamedDirectories.
     */
    @Test
    public void testMoveFiles() {
        System.out.println("moveFiles");
        DestinationNamedDirectories instance = new DestinationNamedDirectories(destDir);
        instance.moveFiles(input);
        List<FileMetaData> outputFiles = new DirectorySource(destDir).getFiles();
        
        //input.stream().forEach(x -> System.out.println("in: " + x.getPath().toString()));
        //outputFiles.stream().forEach(x -> System.out.println(x.toString()));
        
        boolean allFilesMoved = true;
        for(FileMetaData inputFile : input) {
            boolean match = false;
            for(FileMetaData movedFile : outputFiles) {
                if (inputFile.getPath().getFileName().equals(movedFile.getPath().getFileName()))
                    match = true;
            }
            if (match == false)
                allFilesMoved = false;
        }
        
        assertTrue(allFilesMoved);
    }
    
}
