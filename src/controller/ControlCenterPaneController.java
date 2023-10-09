package controller;

import model.MetroEventsEnum;
import model.MetroFacade;
import model.MetroGate;
import model.observer.Observer;
import view.panels.ControlCenterPane;

/* Authors: Nino Verstraeten, Kevin Verbruggen */
public class ControlCenterPaneController implements Observer {

    private final MetroFacade facade;

    private ControlCenterPane controlCenterPane;

    private int soldTicketsInt = 0;
    private double totalRevenue = 0.0;

    public ControlCenterPaneController(MetroFacade metroFacade, ControlCenterPane controlCenterPane) {
        this.facade = metroFacade;
        facade.schrijfInVoorEvent(MetroEventsEnum.SCAN_METROCARD, this);
        facade.schrijfInVoorEvent(MetroEventsEnum.DEACTIVATE_METROGATE, this);
        facade.schrijfInVoorEvent(MetroEventsEnum.SECURITY_BREACH, this);
        this.controlCenterPane = controlCenterPane;
        facade.schrijfInVoorEvent(MetroEventsEnum.BUY_METROCARD, this);
        facade.schrijfInVoorEvent(MetroEventsEnum.BUY_METROCARD_TICKETS, this);
        controlCenterPane.setTheController(this);
        controlCenterPane.initialize();
    }

    public void openMetroStation() {
        facade.openMetroStation();
    }

    public void closeMetroStation() {
        facade.closeMetroStation();
    }

    @Override
    public void update(MetroEventsEnum eventsEnum) {
        switch (eventsEnum) {
            case DEACTIVATE_METROGATE:
            case SCAN_METROCARD:
                for (MetroGate gate : facade.getMetroGateList()) {
                    controlCenterPane.setTicketsScanned(gate.getId(), gate.getMetroCardsScanned());
                }
                break;
            case SECURITY_BREACH:
                for (MetroGate gate : facade.getMetroGateList()) {
                    if (gate.getSecurityBreach() != null) {
                        controlCenterPane.addAlert(gate.getSecurityBreach());
                        gate.setSecurityBreach(null);
                    }
                }
                break;
            case BUY_METROCARD:
                increaseSoldTickets(2);
                increaseTotalRevenue(15);
                break;
            case BUY_METROCARD_TICKETS:
                increaseSoldTickets(facade.getLastAmountOfTickets());
                increaseTotalRevenue(facade.getLastInvoicePrice());
                break;
        }
    }

    public void activateMetroGate(int gateID) {
        try {
            facade.activateGate(gateID);
            controlCenterPane.updateGateLabel(facade.getMetroGateList().get(gateID-1), "Active");
        } catch (IllegalArgumentException | IllegalStateException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deactivateMetroGate(int gateID) {
        try {
            facade.deactivateGate(gateID);
            controlCenterPane.updateGateLabel(facade.getMetroGateList().get(gateID-1), "Inactive");
        } catch (IllegalArgumentException | IllegalStateException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void increaseSoldTickets(int amount) {
        soldTicketsInt += amount;
        controlCenterPane.setSoldTickets(soldTicketsInt);
    }

    public void increaseTotalRevenue(double amount) {
        totalRevenue += amount;
        controlCenterPane.setTotalRevenue(totalRevenue);
    }

    public MetroFacade getFacade() {
        return facade;
    }
}
