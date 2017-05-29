package it.morfoza;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PiggyServiceImpl implements PiggyService {

    private PiggyRepository repository;
    private PaymentRepository paymentRepository;

    public PiggyServiceImpl(PiggyRepository repository, PaymentRepository paymentRepository) {
        this.repository = repository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<PiggyBank> getAll() {
        return repository.getAll();
    }

    @Override
    public PiggyBank getById(long id) {
        return repository.getById(id);
    }

    @Override
    public long add(PiggyBank piggyBank) {
        return repository.add(piggyBank);
    }

    @Override
    public void delete(long id) {
        repository.delete(id);
    }

    @Override
    public void pay(long piggyBankId, Money amount, String email) {
        PiggyBank piggyBank = repository.getById(piggyBankId);
        piggyBank.payIn(amount);
        repository.update(piggyBank);

        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setEmail(email);
        payment.setPiggyBankId(piggyBankId);
        paymentRepository.add(payment);

    }

    @Override
    public List<Payment> getPaymentsForPiggyBank(long piggyBankId) {
        return paymentRepository.getByPiggyBank(piggyBankId);
    }
}
