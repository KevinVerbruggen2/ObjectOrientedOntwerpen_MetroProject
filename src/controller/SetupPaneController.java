package controller;

import model.MetroFacade;
import view.panels.SetupPane;

import java.util.ArrayList;

/* Authors: Arno Robeys */
public class SetupPaneController {

    MetroFacade facade;
    SetupPane theView;

    public SetupPaneController(MetroFacade metroFacade, SetupPane setupPanePane) {
        this.facade = metroFacade;
        this.theView = setupPanePane;
        setupPanePane.setTheController(this);
        theView.initialize();
    }

    public String[] getDiscounts() {
        return facade.getDiscounts();
    }

    public void saveStrategy(String strategy) {
        facade.saveStrategy(strategy);
    }

    public void saveDiscounts(ArrayList<String> selectedDiscounts) {
        facade.saveDiscounts(selectedDiscounts);
    }
}
