package br.com.autosoft.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.autosoft.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

    //@Query("SELECT obj FROM Customer obj WHERE obj.name like :name")
    List<Customer> findByName(String name);
}
