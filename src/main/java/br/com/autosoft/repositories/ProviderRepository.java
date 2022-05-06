package br.com.autosoft.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.autosoft.entities.Provider;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Integer>{

    //@Query("SELECT obj FROM Provider obj WHERE obj.name like :name")
    List<Provider> findByName(String name);
    
}
