/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.view.rulepanes;

import javafx.scene.layout.FlowPane;

/**
 *
 * @author Eric
 */
public interface UIField {
    FlowPane getFlowPane();
    Class getType();
    Object getInput();
}
