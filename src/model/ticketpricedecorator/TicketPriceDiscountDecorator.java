package model.ticketpricedecorator;

import model.MetroCard;

/* Authors: Arno Robeys, Gerald Waerseggers*/
public abstract class TicketPriceDiscountDecorator extends TicketPrice{

    private TicketPrice ticketPrice;

    public TicketPriceDiscountDecorator(TicketPrice ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public TicketPriceDiscountDecorator(TicketPrice ticketPrice, boolean isStudent, boolean is64Plus, boolean is24Min, MetroCard metroCard) {
        this.ticketPrice = ticketPrice;
        setIsStudent(isStudent);
        setIs64Plus(is64Plus);
        setIs24Min(is24Min);
        setAttribute(metroCard);
    }

    public double getPrice() {
        return ticketPrice.getPrice();
    }

    public String getPriceText() {
        return ticketPrice.getPriceText();
    }
}
