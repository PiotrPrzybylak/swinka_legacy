package it.morfoza;

public class PiggyBank {

    private Long id;
    private String name;
    private String date;
    private Money target;
    private Money current;
    private String description;
    private String long_description;
    private String url_image;


    public PiggyBank(String name, String date, Money target, Money current, String description, String long_description,String url_image) {
        this.name = name;
        this.date = date;
        this.target = target;
        this.current = current;
        this.description=description;
        this.long_description=long_description;
        this.url_image=url_image;
    }

    public String getName() {
        return name;
    }


    public double getPrice() {
        return target.getDoubleValue();
    }

    public String getDate() {
        return date;
    }

    public String toString() {
        return name  + date + " target: " + target;
    }

    public boolean isFree() {
        return target.getDoubleValue() == 0;
    }

    public String getDescription(){ return description;}
    public String getLong_description(){ return long_description;}
    public String getUrl_image(){return  url_image;}
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
