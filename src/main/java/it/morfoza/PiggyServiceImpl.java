package it.morfoza;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PiggyServiceImpl implements PiggyService {

    private PiggyRepository repository;

    public PiggyServiceImpl(PiggyRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PiggyBank> getByCity(String City) {
        return repository.getByCity(City);
    }

    @Override
    public List<PiggyBank> getAll() {
        return repository.getAllEvents();
    }
}
