package br.com.autosoft.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.autosoft.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

    //@Query("SELECT obj FROM Product obj WHERE obj.name like :name ORDER BY obj.id")
    List<Product> findByName(String name);
    
}
