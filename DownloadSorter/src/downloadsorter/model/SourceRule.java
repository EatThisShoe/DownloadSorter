/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.model;

import java.util.List;

/**
 *
 * @author Eric
 */
public interface SourceRule extends Rule {
    public List<FileMetaData> getFiles();
}
