/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.Factories;

import downloadsorter.model.DestinationNamedDirectories;
import downloadsorter.model.DestinationRule;

/**
 *
 * @author Eric
 */
public class DestinationFactory {
    public static DestinationRule createDestinationRule(String fileInput) {
        String[] args = fileInput.split(",");
        if (args.length > 0) {
            if (args[0].equals("DestinationNamedDirectories") || args[0].equals("Named Directories"))
                return new DestinationNamedDirectories(args);
        }
        return null;
    }
}
