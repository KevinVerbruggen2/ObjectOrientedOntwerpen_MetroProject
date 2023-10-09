package model.ticketpricedecorator;

import model.MetroCard;

/* Authors: Arno Robeys, Gerald Waerseggers*/
public class ChristmasLeaveDiscount extends TicketPriceDiscountDecorator{

    private final double DISCOUNT = 0.10;
    public ChristmasLeaveDiscount(TicketPrice ticketPrice, boolean isStudent, boolean is64Plus, boolean is24Min, MetroCard metroCard) {
        super(ticketPrice, isStudent, is64Plus, is24Min, metroCard);
    }

    public double getPrice() {
        return super.getPrice() - DISCOUNT;
    }
    public String getPriceText() {
        return super.getPriceText() + " - 0.10€ Christmas Leave Discount";
    }
}
