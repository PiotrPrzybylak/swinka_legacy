package it.morfoza;


public class Payment {

    private Long id;
    private String name;
    private Money amount;
    private Long piggyBankId;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public Long getPiggyBankId() {
        return piggyBankId;
    }

    public void setPiggyBankId(Long piggyBankId) {
        this.piggyBankId = piggyBankId;
    }
}
