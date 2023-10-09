package model.metrogatestates;

/* Authors: Kevin Verbruggen, Nino Verstraeten, */
public interface MetroGateState {

    default void scanMetroCard(MetroGateStateContext context) {
        throw new IllegalArgumentException("MetroCard cannot be scanned because gate is " + context.getState().toString());
    }

    default void walkThroughGate(MetroGateStateContext context) {
        throw new IllegalArgumentException("Security breach! Gate is " + context.getState().toString());
    }

    default void activateGate(MetroGateStateContext context) {
        throw new IllegalArgumentException("Gate cannot be activated because gate is " + context.getState().toString());
    }

    default void deactivateGate(MetroGateStateContext context) {
        throw new IllegalArgumentException("Gate cannot be deactivated because gate is " + context.getState().toString());
    }
}
