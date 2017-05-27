package it.morfoza;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

public class PiggyBank {

    private Long id;
    private String name;
    private String city;
    private String date;
    private Money target;
    private Money current;


    public PiggyBank(String name, String city, String  date, Money target) {
        this.name = name;
        this.city = city;
        this.date = date;
        this.target = target;
        this.current = new Money(0);

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


    public String getName() {
        return name;
    }

    public Money getTarget() {
        return target;
    }

    public Money getCurrent() {
        return current;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPercentRaised() {
        return (int) (current.getDoubleValue()/target.getDoubleValue()*100);
    }
}
