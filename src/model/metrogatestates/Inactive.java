package model.metrogatestates;

/* Authors: Nino Verstraeten, Kevin Verbruggen*/
public class Inactive implements MetroGateState {

    @Override
    public void activateGate(MetroGateStateContext context) {
        context.setState(new Closed());
    }
    public String toString() {
        return "inactive";
    }

}
