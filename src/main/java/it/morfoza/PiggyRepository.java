package it.morfoza;
import java.util.List;

public interface PiggyRepository {

    List<PiggyBank> getAllEvents();

    List<PiggyBank> getByCity(String City);

    PiggyBank getById(long id);
    long add(PiggyBank piggyBank);

    void delete(long id);
}
