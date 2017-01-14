/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.Factories;

import downloadsorter.model.DestinationRule;
import downloadsorter.model.FansubFilter;
import downloadsorter.model.FilterByList;
import downloadsorter.model.FilterRule;
import java.lang.reflect.Constructor;

/**
 *
 * @author Eric
 */
public class FilterRuleFactory {
    public static FilterRule createFilterRule(String fileInput) {
        String[] args = fileInput.split(",", -1);
        try {
            Class cl = Class.forName(args[0]);
            Class[] parameters = new Class[1];
            parameters[0] = args.getClass();
            FilterRule rule = null;
            try {
                Constructor constructor = cl.getConstructor(parameters);
                rule = (FilterRule) constructor.newInstance((Object) args);
            } catch(Exception e) {
                rule = (FilterRule) cl.newInstance();
            }
            return rule;
        } catch (Exception e) {}
//        if (args.length > 0) {
//            if (args[0].equals("FansubFilter") || args[0].equals("Anime Fansub"))
//                return new FansubFilter();
//            if(args[0].equals(FilterByList.class.getCanonicalName()))
//                return new FilterByList(args);
//        }
        return null;
    }
}
