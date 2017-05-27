package it.morfoza;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

public class PiggyBank {

    private String eventName;
    private String city;
    private String date;
    private Money price;


    public PiggyBank(String eventName, String city, String  date, Money price) {
        this.eventName = eventName;
        this.city = city;
        this.date = date;
        this.price = price;

    }

    public String getEventName() {return eventName;}
    public String getCity() {return city;}
    public double getPrice() {return price.getDoubleValue();}
    public String getDate() {return date;}

    public String toString() {
        return eventName + city + date + " Price: " + price;
    }

    public boolean isFree() {
        return price.getDoubleValue() == 0;
    }



}
