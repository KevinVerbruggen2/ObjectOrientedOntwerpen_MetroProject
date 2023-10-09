package model.ticketpricedecorator;

/* Authors: Arno Robeys*/
public enum TicketPriceDiscountEnum {
    AGE64PLUSDISCOUNT("Age64PlusDiscount"), CHRISTMASLEAVEDISCOUNT("ChristmasLeaveDiscount"), STUDENTDISCOUNT("StudentDiscount"), FREQUENTTRAVELLERDISCOUNT("FrequentTravellerDiscount");

    private final String name;
    TicketPriceDiscountEnum(String name) {this.name = name;}

    public String getName() {
        return name;
    }

    public Class<?> getClassName() throws ClassNotFoundException {
        return Class.forName("model.ticketpricedecorator."+ getName());
    }
}
