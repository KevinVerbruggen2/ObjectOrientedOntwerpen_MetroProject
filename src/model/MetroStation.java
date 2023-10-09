package model;

import model.metrostationstates.MetroStationState;
import model.metrostationstates.MetroStationStateContext;

import java.util.ArrayList;
import java.util.List;

/* Authors: Nino Verstraeten, Kevin Verbruggen */
public class MetroStation {

    private MetroStationStateContext context;
    List<MetroGate> metroGateList = new ArrayList<>();

    public MetroStation() {
        metroGateList.add(new MetroGate(1));
        metroGateList.add(new MetroGate(2));
        metroGateList.add(new MetroGate(3));
        context = new MetroStationStateContext();
    }

    public void scanMetroCard(int gateId, int metroCardId) {
        metroGateList.get(gateId-1).scanMetroCard(metroCardId);
    }

    public void walkThroughGate(int gateId, int metroCardId) {
        metroGateList.get(gateId-1).walkThroughGate(metroCardId);
    }

    public void activateGate(int gateId){
        metroGateList.get(gateId-1).activateGate();
    }

    public void deactivateGate(int gateId){
        metroGateList.get(gateId-1).deactivateGate();
    }

    public List<MetroGate> getMetroGateList() {
            return metroGateList;
    }

    public void openStation(){
        context.open();
    }

    public void closeStation(){
        context.close();
    }

    public MetroStationStateContext getContext(){
        return context;
    }

    public MetroStationState getState(){
        return context.getState();
    }
}
