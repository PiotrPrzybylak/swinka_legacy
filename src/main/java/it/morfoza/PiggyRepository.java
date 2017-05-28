package it.morfoza;

import java.util.List;

public interface PiggyRepository {

    List<PiggyBank> getAll();

    PiggyBank getById(long id);

    long add(PiggyBank piggyBank);

    void delete(long id);
}
