package model.ticketpricedecorator;

import model.MetroCard;

/* Authors: Arno Robeys, Gerald Waerseggers*/
public class StudentDiscount extends TicketPriceDiscountDecorator{

    private final double DISCOUNT = 0.25;
    public StudentDiscount(TicketPrice ticketPrice, boolean isStudent, boolean is64Plus, boolean is24Min, MetroCard metroCard) {
        super(ticketPrice, isStudent, is64Plus, is24Min, metroCard);
    }

    public double getPrice() {
        return getIsStudent() ? super.getPrice() - DISCOUNT : super.getPrice();
    }
    public String getPriceText() {
        return getIsStudent()? super.getPriceText() + " - 0,25€ Student Discount" : super.getPriceText();
    }
}
