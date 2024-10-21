package br.com.autosoft.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tb_product")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    
    @Column(name = "factory_code")
    private String factoryCode;

    // @JsonIgnore
    // @ManyToOne
    // @JoinColumn(name = "provider_id")
    // private Provider provider;

    @Column(name = "group_Family")
    private String groupFamily;

    @Column(name = "sub_group")
    private String subGroup;
    private String application;
    private Double price;
    private String brand;
}
