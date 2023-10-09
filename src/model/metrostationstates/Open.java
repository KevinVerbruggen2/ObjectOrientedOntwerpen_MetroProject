package model.metrostationstates;

/* Authors: Nino Verstraeten, Kevin Verbruggen */
public class Open implements MetroStationState {

    @Override
    public void close(MetroStationStateContext context) {
        context.setState(new Closed());
    }

    @Override
    public void open(MetroStationStateContext context) {
        System.out.println("The metro station is already open");
    }

    @Override
    public String toString() {
        return "Open";
    }
}