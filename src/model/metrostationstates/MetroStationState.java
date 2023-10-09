package model.metrostationstates;

/* Authors: Nino Verstraeten, Kevin Verbruggen */
public interface MetroStationState {

    default void open(MetroStationStateContext context) {
        throw new IllegalStateException("Cannot open the metro station because the state is " + context.getState().toString());
    }

    default void close(MetroStationStateContext context) {
        throw new IllegalStateException("Cannot close the metro station because the state is " + context.getState().toString());
    }
}
