package controller;

import model.MetroEventsEnum;
import model.MetroFacade;
import model.observer.Observer;
import view.panels.MetroCardOverviewPane;

/* Authors: Nino Verstraeten, Kevin Verbruggen */
public class MetroCardOverviewPaneController implements Observer {
    private final MetroFacade facade;
    public MetroCardOverviewPane metroCardOverviewPane;

    public MetroCardOverviewPaneController(MetroFacade metroFacade, MetroCardOverviewPane metroCardOverviewPane) {
        this.facade = metroFacade;
        this.metroCardOverviewPane = metroCardOverviewPane;
        metroCardOverviewPane.setController(this);
        metroFacade.schrijfInVoorEvent(MetroEventsEnum.BUY_METROCARD,this);
        metroFacade.schrijfInVoorEvent(MetroEventsEnum.OPEN_METROSTATION,this);
        metroFacade.schrijfInVoorEvent(MetroEventsEnum.CLOSE_METROSTATION,this);
        metroFacade.schrijfInVoorEvent(MetroEventsEnum.BUY_METROCARD_TICKETS,this);
        metroFacade.schrijfInVoorEvent(MetroEventsEnum.SCAN_METROCARD,this);
    }

    @Override
    public void update(MetroEventsEnum eventsEnum) {
        metroCardOverviewPane.updateMetroCardList(facade.getMetroCardList());
    }
}
