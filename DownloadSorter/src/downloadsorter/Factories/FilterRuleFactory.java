/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.Factories;

import downloadsorter.Filters.FansubFilter;
import downloadsorter.Filters.FilterRule;

/**
 *
 * @author Eric
 */
public class FilterRuleFactory {
    public static FilterRule createFilterRule(String fileInput) {
        String[] args = fileInput.split(",");
        if (args.length > 0) {
            if (args[0].equals("FansubFilter"))
                return new FansubFilter();
        }
        return null;
    }
}
