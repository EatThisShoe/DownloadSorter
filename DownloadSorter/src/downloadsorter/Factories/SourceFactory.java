/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.Factories;

import downloadsorter.model.DirectorySource;
import downloadsorter.model.SourceRule;

/**
 *
 * @author Eric
 */
public class SourceFactory {
    public static SourceRule createSourceRule(String fileInput) {
        String[] parameters = fileInput.split(",");
        if (parameters.length > 0) {
            if (parameters[0].equals("DirectorySource") || parameters[0].equals("Directory"))
                return new DirectorySource(parameters);
        }
        return null;
    }
}
