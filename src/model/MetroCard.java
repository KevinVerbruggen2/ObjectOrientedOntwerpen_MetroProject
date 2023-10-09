package model;


import java.time.LocalDate;

/* Authors: Kevin Verbruggen, Nino Verstraeten, Arno Robeys */
public class MetroCard {

    private final int id;
    private String monthYear;
    private int availableRides;
    private int usedRides;

    public MetroCard(int id, String monthYear, int availableRides, int usedRides) {
        this.id = id;
        this.monthYear = monthYear;
        this.availableRides = availableRides;
        this.usedRides = usedRides;
    }

    public int getId() {
        return id;
    }

    public String getMonthYear() {
        return monthYear;
    }

    public LocalDate getExpirationDate() {
        int month = Integer.parseInt(monthYear.split("#")[0]);
        int year = Integer.parseInt(monthYear.split("#")[1]);
        return LocalDate.of(year, month, 1);
    }

    public int getAvailableRides() {
        return availableRides;
    }

    public int getUsedRides() {
        return usedRides;
    }

    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
    }

    public void setAvailableRides(int availableRides) {
        this.availableRides = availableRides;
    }

    public void setUsedRides(int usedRides) {
        this.usedRides = usedRides;
    }

    public void useRide() throws IllegalStateException{
        availableRides--;
        usedRides++;
    }

    @Override
    public String toString() {
        return id+";"+ monthYear+";"+ availableRides+";" + usedRides;
    }
}
