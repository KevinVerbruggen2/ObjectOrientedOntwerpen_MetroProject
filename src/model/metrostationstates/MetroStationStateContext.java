package model.metrostationstates;

/* Authors: Nino Verstraeten, Kevin Verbruggen */
public class MetroStationStateContext {

    MetroStationState state;

    public MetroStationStateContext() {
        state = new Closed();
    }

    public void setState(MetroStationState state) {
        this.state = state;
    }

    public MetroStationState getState() {
        return state;
    }

    public void open() {
        state.open(this);
    }

    public void close() {
        state.close(this);
    }
}
