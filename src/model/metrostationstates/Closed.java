package model.metrostationstates;

/* Authors: Nino Verstraeten, Kevin Verbruggen */
public class Closed implements MetroStationState {

    @Override
    public void open(MetroStationStateContext context) {
        context.setState(new Open());
    }

    @Override
    public String toString() {
        return "Closed";
    }
}
