package model;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.database.MetroCardDatabase;
import model.database.strategy.LoadSaveStrategyFactory;
import model.metrostationstates.Closed;
import model.metrostationstates.Open;
import model.observer.Observer;
import model.observer.Subject;
import model.ticketpricedecorator.TicketPrice;
import model.ticketpricedecorator.TicketPriceFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/* Authors: Nino Verstraeten, Arno Robeys, Kevin Verbruggen */
public class MetroFacade implements Subject {
    HashMap<MetroEventsEnum, ArrayList<Observer>> events = new HashMap<>();
    MetroCardDatabase metroCardDatabase = new MetroCardDatabase();
    MetroStation metroStation = new MetroStation();

    private final TicketPriceFactory ticketPriceFactory = TicketPriceFactory.getInstance();


    public void schrijfInVoorEvent(MetroEventsEnum e,Observer o){
        if (events.containsKey(e)){
            events.get(e).add(o);
        } else {
            ArrayList<Observer> observers1=new ArrayList<>();
            observers1.add(o);
            events.put(e,observers1);
        }
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(MetroEventsEnum metroEventsEnum) {
        if (events != null || events.size() != 0) {
            for (Observer o : events.get(metroEventsEnum)) {
                try {
                    o.update(metroEventsEnum);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void openMetroStation() {
        try {
            metroCardDatabase.setLoadSaveStrategy(LoadSaveStrategyFactory.getInstance().createLoadSaveStrategy());
            metroCardDatabase.load();
            metroStation.openStation();
        } catch (BiffException | IOException e) {
            throw new RuntimeException(e);
        }
        notifyObservers(MetroEventsEnum.OPEN_METROSTATION);
    }

    public void closeMetroStation() {
        try {
            metroCardDatabase.setLoadSaveStrategy(LoadSaveStrategyFactory.getInstance().createLoadSaveStrategy());
            metroCardDatabase.save();
            metroCardDatabase.removeAllMetroCards();
            metroStation.closeStation();

        } catch (IOException | BiffException | WriteException e) {
            throw new RuntimeException(e);
        }
        notifyObservers(MetroEventsEnum.CLOSE_METROSTATION);
    }

    public ArrayList<MetroCard> getMetroCardList(){
        return metroCardDatabase.getMetroCardList();
    }

    public ArrayList<Integer> getMetroCardIDList(){
        return metroCardDatabase.getMetroCardIdList();
    }

    public void buyNewMetroCard(){
        if (metroStation.getState() instanceof Open){
            metroCardDatabase.addNewMetroCard();
            notifyObservers(MetroEventsEnum.BUY_METROCARD);
        }
    }

    public void saveStrategy(String strategy) {
        LoadSaveStrategyFactory.getInstance().saveStrategy(strategy);
    }

    public void buyMetroCardTickets(int metroCard, int amount, double price) {
        metroCardDatabase.addRides(metroCard, amount, price);
        notifyObservers(MetroEventsEnum.BUY_METROCARD_TICKETS);
    }

    public double getPrice(boolean is24Min, boolean is64Plus, boolean isStudent, MetroCard metroCard) {
        TicketPrice ticket = ticketPriceFactory.createTicketPrice(is24Min, is64Plus, isStudent, metroCard);
        return ticket.getPrice();
    }

    private boolean isMetroCardExpired(MetroCard metroCard) {
        return metroCard.getExpirationDate().plusYears(1).plusMonths(1).isBefore(LocalDate.now());
    }

    public String getPriceText(boolean is24Min, boolean is64Plus, boolean isStudent, MetroCard metroCard) {
        if(isMetroCardExpired(metroCard)) {
            throw new IllegalStateException("This metrocard is expired. You can't buy tickets with this metrocard.");
        }
        TicketPrice ticket = ticketPriceFactory.createTicketPrice(is24Min, is64Plus, isStudent, metroCard);
        return ticket.getPriceText();
    }

    public void saveDiscounts(ArrayList<String> selectedDiscounts) {
        ticketPriceFactory.saveDiscounts(selectedDiscounts);
    }


    public void scanMetroCard(int gateID, Object metroCardIdStr) throws IllegalStateException {
        int metroCardId = (Integer) metroCardIdStr;
        MetroCard metroCard = metroCardDatabase.getMetroCardById(metroCardId);

        LocalDateTime now = LocalDateTime.now();
        if (isMetroCardExpired(metroCard)) throw new IllegalStateException("Card is expired");
        if (metroCard.getAvailableRides() <= 0) throw new IllegalStateException("No rides available");

        metroStation.scanMetroCard(gateID, metroCardId);
        metroCard.useRide();
        notifyObservers(MetroEventsEnum.SCAN_METROCARD);
    }

    public void walkThroughGate(int gateID, Object metroCardObj) throws IllegalStateException {
        int metroCardId = (Integer) metroCardObj;
        metroStation.walkThroughGate(gateID, metroCardId);
    }

    public void activateGate(int gateID) throws IllegalStateException {
        if (metroStation.getState() instanceof Closed) {
            throw new IllegalStateException("Metro station is closed");
        }
        metroStation.activateGate(gateID);
        notifyObservers(MetroEventsEnum.ACTIVATE_METROGATE);
    }

    public void deactivateGate(int gateID) throws IllegalStateException {
        metroStation.deactivateGate(gateID);
        notifyObservers(MetroEventsEnum.DEACTIVATE_METROGATE);
    }

    public List<MetroGate> getMetroGateList(){
        return metroStation.getMetroGateList();
    }

    public double getLastInvoicePrice() {
        return metroCardDatabase.getLastInvoicePrice();
    }

    public int getLastAmountOfTickets() {
        return metroCardDatabase.getLastAmountOfTickets();
    }

    public String[] getDiscounts() {
        return ticketPriceFactory.getDiscounts();
    }


}
