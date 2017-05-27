package it.morfoza;

public class PiggyBank {

    private Long id;
    private String name;
    private String city;
    private String date;
    private Money target;
    private Money current;
    private String description;
    private String long_description;


    public PiggyBank(String name, String city, String date, Money target, String description,String long_description) {
        this.name = name;
        this.city = city;
        this.date = date;
        this.target = target;
        this.current = new Money(0);
        this.description=description;
        this.long_description=long_description;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public double getPrice() {
        return target.getDoubleValue();
    }

    public String getDate() {
        return date;
    }

    public String toString() {
        return name + city + date + " Price: " + target;
    }

    public boolean isFree() {
        return target.getDoubleValue() == 0;
    }

    public String getDescription(){ return description;}
    public String getLong_description(){ return long_description;}
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
    public Money getMisingAmount(){
       return new Money(target.getDoubleValue() - current.getDoubleValue());
    }
    public int getPercentRaised() {
        return (int) (current.getDoubleValue() / target.getDoubleValue() * 100);
    }

    public void payIn(Money amount) {
        current = current.add(amount);

    }
}
