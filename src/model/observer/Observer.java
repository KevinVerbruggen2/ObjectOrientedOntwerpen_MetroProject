package model.observer;

import model.MetroEventsEnum;

/* Authors: Kevin Verbruggen, Nino Verstraeten*/
public interface Observer {
    public void update(MetroEventsEnum eventsEnum) throws Exception;
}
