/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.model;

/**
 *
 * @author Eric
 */
public interface Rule {
    String getFXMLPath();
    String getDescription();
    @Override
    String toString();
}
