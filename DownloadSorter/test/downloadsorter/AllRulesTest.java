/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter;

import downloadsorter.model.DestinationNamedDirectories;
import downloadsorter.model.Rule;
import downloadsorter.model.DirectorySource;
import downloadsorter.model.FansubFilter;
import downloadsorter.model.FilterByList;
import downloadsorter.model.Rule;
import downloadsorter.model.MoveAndList;
import downloadsorter.model.Rule;
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
    public void testGetRules() {
        System.out.println("getRules");
        AllRules instance = AllRules.getInstance();
        Rule directorySource = new DirectorySource();
        Rule filterByList = new FilterByList();
        Rule fansubFilter = new FansubFilter();
        Rule namedDir = new DestinationNamedDirectories();
        Rule moveList = new MoveAndList();
        List<Rule> result = instance.getRules();
        assertTrue(result.size() > 0);
        assertTrue(result.stream().anyMatch(rule -> rule.getClass() == directorySource.getClass()));
        assertTrue(result.stream().anyMatch(rule -> rule.getClass() == filterByList.getClass()));
        assertTrue(result.stream().anyMatch(rule -> rule.getClass() == fansubFilter.getClass()));
        assertTrue(result.stream().anyMatch(rule -> rule.getClass() == namedDir.getClass()));
        assertTrue(result.stream().anyMatch(rule -> rule.getClass() == moveList.getClass()));
    }
}
