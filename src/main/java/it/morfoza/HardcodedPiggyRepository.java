package it.morfoza;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Profile("demo")
public class HardcodedPiggyRepository implements PiggyRepository {

    private long nextId;

    private List<PiggyBank> piggyBankList = new ArrayList<>();

    public HardcodedPiggyRepository() {
        piggyBankList.add(new PiggyBank("Wyjazd wakacyjny", "Warszawa", "2016-12-03", new Money(30000)));
        piggyBankList.add(new PiggyBank("Rehabilitacja", "Wrocław", "12.10", new Money(1200)));
        piggyBankList.add(new PiggyBank("Samochód", "Łódź", "09.12", new Money(50000)));
        piggyBankList.add(new PiggyBank("Wózek inwalidzki", "Kraków", "22.10", new Money(15000)));
        for (PiggyBank piggyBank : piggyBankList) {
            piggyBank.setId(++nextId);
        }
    }

    @Override
    public List<PiggyBank> getAllEvents() {
        return piggyBankList;
    }

    @Override
    public List<PiggyBank> getByCity(String city) {
        List<PiggyBank> chosenPiggyBankList = new ArrayList<>();
        for (PiggyBank piggyBank : piggyBankList) {
            if (piggyBank.getCity().equals(city)) {
                chosenPiggyBankList.add(piggyBank);
            }
        }
        return chosenPiggyBankList;
    }

    @Override
    public PiggyBank getById(long id) {
        for (PiggyBank piggyBank : piggyBankList) {
            if (piggyBank.getId() == id) {
                return piggyBank;
            }
        }
        return null;
    }

    @Override
    public long add(PiggyBank piggyBank) {
        piggyBank.setId(++nextId);
        piggyBankList.add(piggyBank);
        return piggyBank.getId();
    }

    @Override
    public void delete(long id) {
        PiggyBank piggyBank = getById(id);
        piggyBankList.remove(piggyBank);
    }

}
