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
        piggyBankList.add(new PiggyBank("Wyjazd wakacyjny",  "2016-12-03", new Money(30000), new Money(0),"","",""));
        piggyBankList.add(new PiggyBank("Rehabilitacja",  "12.10", new Money(1200),new Money(0),"","",""));
        piggyBankList.add(new PiggyBank("Samochód", "09.12", new Money(50000),new Money(0),"","",""));
        piggyBankList.add(new PiggyBank("Wózek inwalidzki", "22.10", new Money(15000),new Money(0),"jakis tam opis","","" ));
        for (PiggyBank piggyBank : piggyBankList) {
            piggyBank.setId(++nextId);
        }
    }

    @Override
    public List<PiggyBank> getAll() {
        return piggyBankList;
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
