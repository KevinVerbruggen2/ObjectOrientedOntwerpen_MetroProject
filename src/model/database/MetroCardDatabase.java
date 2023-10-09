package model.database;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.MetroCard;
import model.database.strategy.LoadSaveStrategy;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeMap;

/* Authors: Kevin Verbruggen, Nino Verstraeten, Arno Robeys */
public class MetroCardDatabase {
    private final TreeMap<Integer, MetroCard> metroCards = new TreeMap<>();
    private LoadSaveStrategy loadSaveStrategy;
    private double lastInvoicePrice = 0;
    private int lastAmountOfTickets = 0;

    public void setLoadSaveStrategy(LoadSaveStrategy loadSaveStrategy) {
        this.loadSaveStrategy = loadSaveStrategy;
    }

    public void load() throws BiffException, IOException {
        metroCards.putAll(loadSaveStrategy.load());
    }
    public void save() throws BiffException, WriteException, IOException {
        loadSaveStrategy.save(metroCards);
    }

    public ArrayList<MetroCard> getMetroCardList() {
        return new ArrayList<>(metroCards.values());
    }

    public ArrayList<Integer> getMetroCardIdList(){
        return new ArrayList<>(metroCards.keySet());
    }

    public void addNewMetroCard() {
        int id = metroCards.size() == 0 ? 1 : metroCards.lastKey() + 1;
        LocalDateTime now = LocalDateTime.now();
        String monthYear = now.getMonthValue() + "#" + now.getYear();
        metroCards.put(id, new MetroCard(id, monthYear, 2, 0));
    }

    public void addRides(int id, int amount, double price) {
        setLastAmountOfTickets(amount);
        setLastInvoicePrice(price);
        MetroCard metroCard = metroCards.get(id);
        metroCard.setAvailableRides(metroCard.getAvailableRides() + amount);
        LocalDateTime now = LocalDateTime.now();
        String monthYear = now.getMonthValue() + "#" + now.getYear();
        metroCard.setMonthYear(monthYear);
    }

    public MetroCard getMetroCardById(int id) {
        return metroCards.get(id);
    }

    public void removeAllMetroCards() {
        metroCards.clear();
    }

    public void setLastInvoicePrice(double lastInvoicePrice) {
        this.lastInvoicePrice = lastInvoicePrice;
    }

    public double getLastInvoicePrice() {
        return lastInvoicePrice;
    }

    public void setLastAmountOfTickets(int lastAmountOfTickets) {
        this.lastAmountOfTickets = lastAmountOfTickets;
    }

    public int getLastAmountOfTickets() {
        return lastAmountOfTickets;
    }
}
