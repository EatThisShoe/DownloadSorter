/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter;

import downloadsorter.model.DestinationNamedDirectories;
import downloadsorter.model.DestinationRule;
import downloadsorter.model.DirectorySource;
import downloadsorter.model.FansubFilter;
import downloadsorter.model.FilterByList;
import downloadsorter.model.FilterRule;
import downloadsorter.model.MoveAndList;
import downloadsorter.model.SourceRule;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Eric
 */
public class AllRulesTest {
    
    public AllRulesTest() {
    }

    @Test
    public void testGetSourceRules() {
        System.out.println("getSourceRules");
        AllRules instance = AllRules.getInstance();
        SourceRule directorySource = new DirectorySource();
        List<SourceRule> result = instance.getSourceRules();
        Boolean hasDirectorySource = result.stream().anyMatch(rule -> rule.getClass() == directorySource.getClass());
        assertTrue(result.size() > 0);
        assertTrue(hasDirectorySource);
    }

    @Test
    public void testGetFilterRules() {
        System.out.println("getFilterRules");
        AllRules instance = AllRules.getInstance();
        FilterRule filterByList = new FilterByList();
        FilterRule fansubFilter = new FansubFilter();
        List<FilterRule> result = instance.getFilterRules();
        assertTrue(result.size() > 0);
        assertTrue(result.stream().anyMatch(rule -> rule.getClass() == filterByList.getClass()));
        assertTrue(result.stream().anyMatch(rule -> rule.getClass() == fansubFilter.getClass()));
    }

    @Test
    public void testGetDestinationRules() {
        System.out.println("getDestinationRules");
        AllRules instance = AllRules.getInstance();
        DestinationRule namedDir = new DestinationNamedDirectories();
        DestinationRule moveList = new MoveAndList();
        List<DestinationRule> result = instance.getDestinationRules();
        assertTrue(result.size() > 0);
        assertTrue(result.stream().anyMatch(rule -> rule.getClass() == namedDir.getClass()));
        assertTrue(result.stream().anyMatch(rule -> rule.getClass() == moveList.getClass()));
    }
}
