package com.yasinkucuker.arzum_iletisim.service.impl;

import com.yasinkucuker.arzum_iletisim.model.Sale;
import com.yasinkucuker.arzum_iletisim.repository.SaleRepository;
import com.yasinkucuker.arzum_iletisim.service.SaleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository repo;

    public SaleServiceImpl(SaleRepository repo) {
        this.repo = repo;
    }

    @Override
    public Sale save(Sale entity) {
        return repo.save(entity);
    }

    @Override
    public Sale update(Long id, Sale entity) {
        Sale existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found"));

        existing.setProduct(entity.getProduct());
        existing.setCustomer(entity.getCustomer());
        existing.setSaleDate(entity.getSaleDate());
        existing.setAmount(entity.getAmount());

        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Sale getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Sale not found"));
    }

    @Override
    public List<Sale> getAll() {
        return repo.findAll();
    }
}