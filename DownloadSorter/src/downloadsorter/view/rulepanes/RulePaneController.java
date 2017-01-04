/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.view.rulepanes;

import downloadsorter.model.Rule;

/**
 *
 * @author Eric
 */
public interface RulePaneController<T extends Rule> {
     T getRule();
    void setRule(T rule);
}
