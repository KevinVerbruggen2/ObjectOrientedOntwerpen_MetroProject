package model.ticketpricedecorator;

import model.MetroCard;

/* Authors: Gerald Waerseggers*/
public abstract class TicketPrice {
    private boolean is24Min;
    private boolean is64Plus;
    private boolean isStudent;
    private MetroCard attribute;

    public boolean getIs24Min() {
        return is24Min;
    }

    public void setIs24Min(boolean is24Min) {
        this.is24Min = is24Min;
    }

    public boolean getIs64Plus() {
        return is64Plus;
    }

    public void setIs64Plus(boolean is64Plus) {
        this.is64Plus = is64Plus;
    }

    public boolean getIsStudent() {
        return isStudent;
    }

    public void setIsStudent(boolean isStudent) {
        this.isStudent = isStudent;
    }

    public MetroCard getAttribute() {
        return attribute;
    }

    public void setAttribute(MetroCard attribute) {
        this.attribute = attribute;
    }

    public abstract String getPriceText();

    public abstract double getPrice();




}
