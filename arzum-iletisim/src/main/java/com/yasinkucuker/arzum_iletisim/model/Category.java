package com.yasinkucuker.arzum_iletisim.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Category altındaki ürünler
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @ToString.Exclude // Döngüyü kırmak için burayı ekle
    private List<Product> products;
}