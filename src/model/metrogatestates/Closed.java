package model.metrogatestates;

/* Authors: Kevin Verbruggen, Nino Verstraeten*/
public class Closed implements MetroGateState {

    @Override
    public void deactivateGate(MetroGateStateContext context) {
        context.setState(new Inactive());
    }
    @Override
    public void scanMetroCard(MetroGateStateContext context) {
        context.setState(new Open());
    }
    public String toString() {
        return "closed";
    }
}
