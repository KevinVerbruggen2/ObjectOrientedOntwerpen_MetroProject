package controller;

import javafx.scene.control.Label;
import model.MetroEventsEnum;
import model.MetroFacade;
import model.MetroGate;
import model.metrogatestates.Inactive;
import model.metrogatestates.MetroGateState;
import model.observer.Observer;
import view.MetroStationView;

import java.time.LocalDateTime;
import java.util.List;

/* Authors: Nino Verstraeten, Kevin Verbruggen */
public class MetroStationViewController implements Observer {

        private final MetroFacade facade;
        private MetroStationView metroStationView;

        public MetroStationViewController(MetroFacade metroFacade, MetroStationView metroStationView) {
            this.facade = metroFacade;
            this.metroStationView = metroStationView;
            metroStationView.setTheController(this);
            metroStationView.initialize();
            metroFacade.schrijfInVoorEvent(MetroEventsEnum.OPEN_METROSTATION,this);
            metroFacade.schrijfInVoorEvent(MetroEventsEnum.CLOSE_METROSTATION,this);
            metroFacade.schrijfInVoorEvent(MetroEventsEnum.BUY_METROCARD,this);
            metroFacade.schrijfInVoorEvent(MetroEventsEnum.ACTIVATE_METROGATE,this);
            metroFacade.schrijfInVoorEvent(MetroEventsEnum.DEACTIVATE_METROGATE,this);
        }

        @Override
        public void update(MetroEventsEnum eventsEnum) {
            switch (eventsEnum) {
                case OPEN_METROSTATION:
                case BUY_METROCARD:
                    metroStationView.updateMetrocardIDList(facade.getMetroCardIDList());
                    break;
                case ACTIVATE_METROGATE:
                case DEACTIVATE_METROGATE:
                    for (MetroGate gate : facade.getMetroGateList()) {
                        if (gate.getState() instanceof Inactive) {
                            metroStationView.deactivateGate(gate.getId());
                        } else {
                            metroStationView.activateGate(gate.getId());
                        }
                    }
                    break;
            }

        }

        public void scanMetroCard(int gateID, Object metrocardID, Label alert) {
            try {
                facade.scanMetroCard(gateID, metrocardID);
                alert.setText(getMetroGateList().get(gateID-1).getNotification());
            } catch (NullPointerException ex) {
                alert.setText("No scanned card, please try again");
            }catch (IllegalArgumentException | IllegalStateException ex) {
                alert.setText(ex.getMessage());
            }
        }

        public void walkThroughGate(int gateID, Object metrocardID, Label alert){
            try {
                facade.walkThroughGate(gateID, metrocardID);
                alert.setText(getMetroGateList().get(gateID-1).getNotification());
            } catch (IllegalArgumentException | IllegalStateException ex) {
                facade.getMetroGateList().get(gateID-1).setSecurityBreach(LocalDateTime.now().toString().substring(11,16) + " Unauthorized passage at gate: " + gateID);
                facade.notifyObservers(MetroEventsEnum.SECURITY_BREACH);
                alert.setText(ex.getMessage());
            } catch (NullPointerException ex) {
                facade.getMetroGateList().get(gateID-1).setSecurityBreach(LocalDateTime.now().toString().substring(11,16) + " Unauthorized passage at gate: " + gateID);
                facade.notifyObservers(MetroEventsEnum.SECURITY_BREACH);
                alert.setText("Security breach!");
            }
        }

        public List<MetroGate> getMetroGateList(){
            return facade.getMetroGateList();
        }

}
