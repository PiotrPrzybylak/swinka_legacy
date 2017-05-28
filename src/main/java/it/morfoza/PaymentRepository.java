package it.morfoza;

import java.util.List;

public interface PaymentRepository {

    List<Payment> getAll();

    Payment getById(long id);

    long add(Payment payment);

    void delete(long id);
}
