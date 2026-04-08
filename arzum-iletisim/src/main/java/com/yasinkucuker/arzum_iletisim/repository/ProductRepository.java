package com.yasinkucuker.arzum_iletisim.repository;

import com.yasinkucuker.arzum_iletisim.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> { }