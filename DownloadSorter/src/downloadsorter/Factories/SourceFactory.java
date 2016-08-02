/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.Factories;

import downloadsorter.Filters.DirectorySource;
import downloadsorter.Filters.SourceRule;

/**
 *
 * @author Eric
 */
public class SourceFactory {
    public static SourceRule createSourceRule(String fileInput) {
        String[] args = fileInput.split(",");
        if (args.length > 0) {
            if (args[0] == "DirectorySource")
                return new DirectorySource(args);
        }
        return null;
    }
}
