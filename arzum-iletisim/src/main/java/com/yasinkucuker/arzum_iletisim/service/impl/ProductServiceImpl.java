package com.yasinkucuker.arzum_iletisim.service.impl;

import com.yasinkucuker.arzum_iletisim.model.Product;
import com.yasinkucuker.arzum_iletisim.repository.ProductRepository;
import com.yasinkucuker.arzum_iletisim.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repo;

    public ProductServiceImpl(ProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public Product save(Product entity) {
        return repo.save(entity);
    }

    @Override
    public Product update(Long id, Product entity) {
        Product existing = repo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        existing.setName(entity.getName());
        existing.setBarcode(entity.getBarcode());
        existing.setImage(entity.getImage());
        existing.setCategory(entity.getCategory());
        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Product getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public List<Product> getAll() {
        return repo.findAll();
    }
}