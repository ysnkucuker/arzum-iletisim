package com.yasinkucuker.arzum_iletisim.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TechnicalService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serviceName;
    private String description;

    private LocalDateTime requestDate;
    private LocalDateTime completionDate;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
