package it.morfoza;

import java.util.List;

public interface PiggyService {
    List<PiggyBank> getByCity(String City);
    List<PiggyBank> getAll();
    PiggyBank getById(long id);
    void delete(long id);
}
