package com.yasinkucuker.arzum_iletisim.service.impl;

import com.yasinkucuker.arzum_iletisim.model.Category;
import com.yasinkucuker.arzum_iletisim.repository.CategoryRepository;
import com.yasinkucuker.arzum_iletisim.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repo;

    public CategoryServiceImpl(CategoryRepository repo) {
        this.repo = repo;
    }

    @Override
    public Category save(Category entity) {
        return repo.save(entity);
    }

    @Override
    public Category update(Long id, Category entity) {
        Category existing = repo.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        existing.setName(entity.getName());
        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Category getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public List<Category> getAll() {
        return repo.findAll();
    }
}