/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.model;

import downloadsorter.view.rulepanes.UIField;
import java.util.List;

/**
 *
 * @author Eric
 */
public interface Rule {
    public List<FileMetaData> process(List<FileMetaData> l);
    String getDescription();
    UIField[] getFields();
    @Override
    String toString();
}
