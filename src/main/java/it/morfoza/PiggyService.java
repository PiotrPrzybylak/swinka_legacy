package it.morfoza;

import java.util.List;

public interface PiggyService {

    List<PiggyBank> getAll();

    PiggyBank getById(long id);

    long add(PiggyBank piggyBank);

    void delete(long id);

    void pay(long id, long amount);
}
