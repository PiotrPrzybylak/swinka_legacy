package it.morfoza;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Profile("demo")
public class HardcodedPiggyRepository implements PiggyRepository {

    private List<PiggyBank> piggyBankList = new ArrayList<>();

    public HardcodedPiggyRepository() {
        piggyBankList.add(new PiggyBank("Salsa Dance", "Warszawa", "2016-12-03", new Money(0)));
        piggyBankList.add(new PiggyBank("Jazz", "Wrocław", "12.10", new Money(120)));
        piggyBankList.add(new PiggyBank("Dupa Dance", "Łódź", "09.12", new Money(666)));
        piggyBankList.add(new PiggyBank("Tańczy się tylko raz", "Kraków", "22.10", new Money(0)));
    }

    @Override
    public List<PiggyBank> getAllEvents() {
        return piggyBankList;
    }

    @Override
    public List<PiggyBank> getByCity(String city) {
        List<PiggyBank> chosenPiggyBankList =new ArrayList<>();
        for (PiggyBank piggyBank : piggyBankList) {
            if (piggyBank.getCity().equals(city)) {
                chosenPiggyBankList.add(piggyBank);
            }
        }
        return chosenPiggyBankList;
    }

}
