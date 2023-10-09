package model.observer;

import jxl.read.biff.BiffException;
import model.MetroEventsEnum;

import java.io.IOException;
import java.util.ArrayList;

/* Authors: Kevin Verbruggen, Nino Verstraeten*/
public interface Subject {
    ArrayList<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer);

    public void removeObserver(Observer observer);

    public void notifyObservers(MetroEventsEnum MetroEventsEnum) throws Exception;
}
