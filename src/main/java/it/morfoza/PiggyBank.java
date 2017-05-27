package it.morfoza;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

public class PiggyBank {

    private String name;
    private String city;
    private String date;
    private Money target;


    public PiggyBank(String name, String city, String  date, Money target) {
        this.name = name;
        this.city = city;
        this.date = date;
        this.target = target;

    }

    public String getEventName() {return name;}
    public String getCity() {return city;}
    public double getPrice() {return target.getDoubleValue();}
    public String getDate() {return date;}

    public String toString() {
        return name + city + date + " Price: " + target;
    }

    public boolean isFree() {
        return target.getDoubleValue() == 0;
    }



}
