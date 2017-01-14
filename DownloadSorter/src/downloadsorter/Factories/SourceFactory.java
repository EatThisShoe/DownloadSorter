/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.Factories;

import downloadsorter.model.DestinationRule;
import downloadsorter.model.DirectorySource;
import downloadsorter.model.SourceRule;
import java.lang.reflect.Constructor;

/**
 *
 * @author Eric
 */
public class SourceFactory {
    public static SourceRule createSourceRule(String fileInput) {
        String[] args = fileInput.split(",", -1);
        try {
            Class cl = Class.forName(args[0]);
            Class[] parameters = new Class[1];
            parameters[0] = args.getClass();
            Constructor constructor = cl.getConstructor(parameters);
            SourceRule rule = (SourceRule) constructor.newInstance((Object) args);
            return rule;
        } catch (Exception e) {}
//        if (parameters.length > 0) {
//            if (parameters[0].equals("DirectorySource") || parameters[0].equals("Directory"))
//                return new DirectorySource(parameters);
//        }
        return null;
    }
}
