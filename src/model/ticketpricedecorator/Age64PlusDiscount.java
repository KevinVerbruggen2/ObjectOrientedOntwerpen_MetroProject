package model.ticketpricedecorator;

import model.MetroCard;

/* Authors: Arno Robeys, Gerald Waerseggers*/
public class Age64PlusDiscount extends TicketPriceDiscountDecorator{

    private final double DISCOUNT = 0.15;
    public Age64PlusDiscount(TicketPrice ticketPrice, boolean isStudent, boolean is64Plus, boolean is24Min, MetroCard metroCard) {
        super(ticketPrice, isStudent, is64Plus, is24Min, metroCard);
    }

    public double getPrice() {
        return getIs64Plus() ? super.getPrice() - DISCOUNT : super.getPrice();
    }
    public String getPriceText() {
        return getIs64Plus()? super.getPriceText() + " - 0.15€ 64+ Age Discount)" : super.getPriceText();
    }
}
