package model;

import model.metrogatestates.MetroGateState;
import model.metrogatestates.MetroGateStateContext;

/* Authors: Kevin Verbruggen, Nino Verstraeten*/
public class MetroGate {

    private int id;
    private MetroGateStateContext context;
    private String notification = "";
    private String securityBreach;
    private int metroCardsScanned = 0;

    public MetroGate(int id) {
        context = new MetroGateStateContext();
        this.id = id;
    }

    public void scanMetroCard(int metroCardID) {
        context.scanMetroCard();
        metroCardsScanned++;
        notification = "MetroCard" + metroCardID + " is scanned";
    }

    public void walkThroughGate(int metroCardID) {
        context.walkTroughGate();
        notification = "MetroCard" + metroCardID + " passed gate";
    }

    public void activateGate() {
        context.activateGate();
        notification = "Gate is activated";
    }

    public void deactivateGate() {
        context.deactivateGate();
        metroCardsScanned = 0;
        notification = "Gate is deactivated";
    }

    public void setState(MetroGateState state){
        context.setState(state);
    }

    public int getId(){
        return id;
    }

    public String getNotification() {
        return notification;
    }

    public MetroGateState getState(){
        return context.getState();
    }

    public int getMetroCardsScanned(){
        return metroCardsScanned;
    }

    public void setSecurityBreach(String securityBreach){
        this.securityBreach = securityBreach;
    }

    public String getSecurityBreach(){
        return securityBreach;
    }
}
