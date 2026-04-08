package com.yasinkucuker.arzum_iletisim.service.impl;

import com.yasinkucuker.arzum_iletisim.model.Transaction;
import com.yasinkucuker.arzum_iletisim.repository.TransactionRepository;
import com.yasinkucuker.arzum_iletisim.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repo;

    public TransactionServiceImpl(TransactionRepository repo) {
        this.repo = repo;
    }

    @Override
    public Transaction save(Transaction entity) {
        return repo.save(entity);
    }

    @Override
    public Transaction update(Long id, Transaction entity) {
        Transaction existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        existing.setCustomer(entity.getCustomer());
        existing.setAmount(entity.getAmount());
        existing.setDescription(entity.getDescription());
        existing.setTransactionDate(entity.getTransactionDate());

        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Transaction getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    @Override
    public List<Transaction> getAll() {
        return repo.findAll();
    }
}