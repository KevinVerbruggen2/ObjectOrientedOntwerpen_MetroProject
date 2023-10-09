package model.metrogatestates;

/* Authors: Nino Verstraeten, Kevin Verbruggen*/
public class MetroGateStateContext {

    MetroGateState state;

    public MetroGateStateContext(){
        state = new Inactive();
    }

    public void scanMetroCard() {
        state.scanMetroCard(this);
    }

    public void walkTroughGate() {
        state.walkThroughGate(this);
    }

    public void activateGate() {
        state.activateGate(this);
    }

    public void deactivateGate() {
        state.deactivateGate(this);
    }

    public MetroGateState getState(){
        return state;
    }

    public void setState(MetroGateState state) {
        this.state = state;
    }
}
