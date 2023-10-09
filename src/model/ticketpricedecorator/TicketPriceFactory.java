package model.ticketpricedecorator;

import model.MetroCard;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

/* Authors: Arno Robeys */
public class TicketPriceFactory {

    private final Properties properties = new Properties();
    private static TicketPriceFactory instance = null;

    private String[] activeDiscounts;

    private TicketPriceFactory() {
        loadProperties();
        activeDiscounts = properties.getProperty("discounts").split(",");
    }

    public static TicketPriceFactory getInstance() {
        if (instance == null) {
            instance = new TicketPriceFactory();
        }
        return instance;
    }

    public TicketPrice createTicketPrice(boolean is24Min, boolean is64Plus, boolean isStudent, MetroCard metroCard){
        loadProperties();
        TicketPrice instance = new BasicTicketPrice();
        try{
            for (String discount : activeDiscounts) {
                if(!discount.trim().isEmpty()) {
                    TicketPriceDiscountEnum classType = TicketPriceDiscountEnum.valueOf(discount);
                    Class<?> clazz = classType.getClassName();
                    Constructor<?> object = clazz.getConstructor(TicketPrice.class, boolean.class, boolean.class, boolean.class, MetroCard.class);
                    instance = (TicketPrice) object.newInstance(instance, isStudent, is64Plus, is24Min, metroCard);
                }
            }
        } catch (Exception ex){
            System.out.println("Error in TicketPriceFactory: " + ex.getMessage());
        }
        return instance;
    }

    public String[] getDiscounts(){
        return activeDiscounts;
    }

    private void loadProperties() {
        try {
            InputStream inputStream = Files.newInputStream(Paths.get("src/bestanden/config.properties"));
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveDiscounts(ArrayList<String> selectedDiscounts) {
        try {
            properties.setProperty("discounts", String.join(",", selectedDiscounts));
            FileOutputStream os = new FileOutputStream("src/bestanden/config.properties");
            properties.store(os, "discounts");
            os.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
