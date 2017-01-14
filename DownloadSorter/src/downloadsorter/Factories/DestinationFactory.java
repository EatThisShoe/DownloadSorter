/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.Factories;

import downloadsorter.model.DestinationNamedDirectories;
import downloadsorter.model.DestinationRule;
import java.lang.reflect.Constructor;

/**
 *
 * @author Eric
 */
public class DestinationFactory {
    public static DestinationRule createDestinationRule(String fileInput) {
        String[] args = fileInput.split(",", -1);
        try {
            Class cl = Class.forName(args[0]);
            Class[] parameters = new Class[1];
            parameters[0] = args.getClass();
            Constructor constructor = cl.getConstructor(parameters);
            DestinationRule rule = (DestinationRule) constructor.newInstance((Object) args);
            return rule;
        } catch (Exception e) {}
//        if (args.length > 0) {
//            if (args[0].equals("DestinationNamedDirectories") || args[0].equals("Named Directories"))
//                return new DestinationNamedDirectories(args);
//        }
        return null;
    }
}
