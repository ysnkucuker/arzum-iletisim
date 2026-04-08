package com.yasinkucuker.arzum_iletisim.service.impl;

import com.yasinkucuker.arzum_iletisim.model.TechnicalService;
import com.yasinkucuker.arzum_iletisim.repository.TechnicalServiceRepository;
import com.yasinkucuker.arzum_iletisim.service.TechnicalServiceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnicalServiceImpl implements TechnicalServiceService {

    private final TechnicalServiceRepository repo;

    public TechnicalServiceImpl(TechnicalServiceRepository repo) {
        this.repo = repo;
    }

    @Override
    public TechnicalService save(TechnicalService entity) {
        return repo.save(entity);
    }

    @Override
    public TechnicalService update(Long id, TechnicalService entity) {
        TechnicalService existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("TechnicalService not found"));

        existing.setServiceName(entity.getServiceName());
        existing.setDescription(entity.getDescription());
        existing.setRequestDate(entity.getRequestDate());
        existing.setCompletionDate(entity.getCompletionDate());
        existing.setProduct(entity.getProduct());

        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public TechnicalService getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("TechnicalService not found"));
    }

    @Override
    public List<TechnicalService> getAll() {
        return repo.findAll();
    }
}