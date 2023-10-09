package model.ticketpricedecorator;

import model.MetroCard;

/* Authors: Arno Robeys, Gerald Waerseggers*/
public class FrequentTravellerDiscount extends TicketPriceDiscountDecorator {

    private final double DISCOUNT = 0.20;
    public FrequentTravellerDiscount(TicketPrice ticketPrice, boolean isStudent, boolean is64Plus, boolean is24Min, MetroCard metroCard) {
        super(ticketPrice, isStudent, is64Plus, is24Min, metroCard);
    }

    public double getPrice() {
        return getAttribute().getUsedRides() > 50 ? super.getPrice() - DISCOUNT : super.getPrice();
    }
    public String getPriceText() {
        return getAttribute().getUsedRides() > 50 ? super.getPriceText() + " - 0.20€ Frequently Traveller Discount" : super.getPriceText();
    }
}
