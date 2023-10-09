package model.metrogatestates;

/* Authors: Nino Verstraeten, Kevin Verbruggen */
public class Open implements MetroGateState{

    @Override
    public void walkThroughGate(MetroGateStateContext context) {
        context.setState(new Closed());
    }
    public String toString() {
        return "open";
    }
}
