package it.morfoza;
import java.util.List;

public interface PiggyRepository {

    List<PiggyBank> getAllEvents();

    List<PiggyBank> getByCity(String City);

    PiggyBank getById(long id);

    void delete(long id);
}
