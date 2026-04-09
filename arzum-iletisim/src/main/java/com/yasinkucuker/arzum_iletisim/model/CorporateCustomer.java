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
public class CorporateCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String contactPerson;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "customer")
    @ToString.Exclude
    private List<Sale> sales;

    @OneToMany(mappedBy = "customer")
    @ToString.Exclude
    private List<Transaction> transactions;
}
