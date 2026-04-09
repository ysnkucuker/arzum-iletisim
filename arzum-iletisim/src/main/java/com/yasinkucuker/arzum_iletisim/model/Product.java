package com.yasinkucuker.arzum_iletisim.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String barcode;

    @Lob
    private byte[] image; // ürün resmi, byte array olarak saklanacak

    @ManyToOne
    @JoinColumn(name = "category_id")
    @ToString.Exclude // Döngüyü kırmak için burayı ekle
    @EqualsAndHashCode.Exclude // Opsiyonel: Karşılaştırma döngülerini de engeller
    private Category category;
}