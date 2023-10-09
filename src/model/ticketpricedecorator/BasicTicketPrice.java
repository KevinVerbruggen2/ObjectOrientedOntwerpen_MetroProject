package model.ticketpricedecorator;

/* Authors: Arno Robeys, Gerald Waerseggers*/
public class BasicTicketPrice extends TicketPrice {

    private final double BASICPRICE = 2.10;
    public double getPrice() {
        return BASICPRICE ;
    }

    public String getPriceText() {
        return "Basic price of ride is 2,10 €";
    }

}
