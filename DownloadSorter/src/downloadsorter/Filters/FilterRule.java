/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.Filters;

import java.nio.file.Path;
import java.util.List;

/**
 *
 * @author Eric
 */
public interface FilterRule {
    public List<Path> filterFiles(List<Path> l);
}
