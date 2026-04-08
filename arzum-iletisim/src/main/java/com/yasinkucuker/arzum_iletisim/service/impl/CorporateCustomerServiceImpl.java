package com.yasinkucuker.arzum_iletisim.service.impl;

import com.yasinkucuker.arzum_iletisim.model.CorporateCustomer;
import com.yasinkucuker.arzum_iletisim.repository.CorporateCustomerRepository;
import com.yasinkucuker.arzum_iletisim.service.CorporateCustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorporateCustomerServiceImpl implements CorporateCustomerService {

    private final CorporateCustomerRepository repo;

    public CorporateCustomerServiceImpl(CorporateCustomerRepository repo) {
        this.repo = repo;
    }

    @Override
    public CorporateCustomer save(CorporateCustomer entity) {
        return repo.save(entity);
    }

    @Override
    public CorporateCustomer update(Long id, CorporateCustomer entity) {
        CorporateCustomer existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("CorporateCustomer not found"));

        existing.setCompanyName(entity.getCompanyName());
        existing.setContactPerson(entity.getContactPerson());
        existing.setEmail(entity.getEmail()); // eksik ekledim
        existing.setPhone(entity.getPhone());

        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public CorporateCustomer getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("CorporateCustomer not found"));
    }

    @Override
    public List<CorporateCustomer> getAll() {
        return repo.findAll();
    }
}