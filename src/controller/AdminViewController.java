package controller;

import model.MetroFacade;
import view.AdminView;

/* Authors:  */
public class AdminViewController {

    AdminView theView;
    MetroFacade facade;

    public AdminViewController(MetroFacade metroFacade, AdminView adminView) {
        this.facade = metroFacade;
        this.theView = adminView;
        theView.setTheController(this);
    }
}
