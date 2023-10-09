package controller;

import model.MetroCard;
import model.MetroEventsEnum;
import model.MetroFacade;
import model.observer.Observer;
import view.MetroTicketView;

/* Authors: Nino Verstraeten, Kevin Verbruggen, Arno Robeys */
public class MetroTicketViewController implements Observer {

    private double ticketPrice;
    private final MetroFacade facade;
    public MetroTicketView metroTicketView;

    public MetroTicketViewController(MetroFacade metroFacade, MetroTicketView metroTicketView) {
        this.facade = metroFacade;
        this.metroTicketView = metroTicketView;
        metroTicketView.setTheController(this);

        metroFacade.schrijfInVoorEvent(MetroEventsEnum.OPEN_METROSTATION,this);
        metroFacade.schrijfInVoorEvent(MetroEventsEnum.CLOSE_METROSTATION,this);
        metroFacade.schrijfInVoorEvent(MetroEventsEnum.BUY_METROCARD,this);
    }
    @Override
    public void update(MetroEventsEnum eventsEnum) {
        metroTicketView.updateMetrocardIDList(facade.getMetroCardIDList());
    }
    public void createNewMetroCard(){
        try {
            facade.buyNewMetroCard();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void calculatePrice(String metroCardID, String rides, boolean isStudent, boolean is64Plus, boolean is26Min) {
        try {
            MetroCard metroCard = facade.getMetroCardList().get(Integer.parseInt(metroCardID)-1);
            String priceText = facade.getPriceText(is26Min, is64Plus, isStudent, metroCard);
            double price = Integer.parseInt(rides) * facade.getPrice(is26Min, is64Plus, isStudent, metroCard);
            ticketPrice = price;
            metroTicketView.updateTotalPriceLabel(String.format("%.2f",price), priceText, true);
        } catch(IllegalStateException e){
            metroTicketView.updateTotalPriceLabel("0", e.getMessage(), false);
        }
    }

    public void buyRides(String metroCardID, String rides) {
        facade.buyMetroCardTickets(Integer.parseInt(metroCardID), Integer.parseInt(rides), ticketPrice);
    }
}
