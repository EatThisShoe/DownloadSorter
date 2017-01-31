/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.model;

import downloadsorter.view.rulepanes.UIField;
import java.util.Map.Entry;

/**
 *
 * @author Eric
 */
public interface Rule {
    String getFXMLPath();
    String getDescription();
    UIField[] getFields();
    @Override
    String toString();
}
