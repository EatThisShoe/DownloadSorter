/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.view.rulepanes;

import downloadsorter.model.FansubFilter;
import downloadsorter.model.Rule;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Eric
 */
public class FansubFilterController implements Initializable, RulePaneController {
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public Rule getRule() {
        return new FansubFilter();
    }

    @Override
    public void setRule(Rule rule) {
        //Fansub filter has no input to set.
    }
    
}
