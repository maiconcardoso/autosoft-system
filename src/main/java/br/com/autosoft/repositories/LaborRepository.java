package br.com.autosoft.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.autosoft.entities.Labor;

@Repository
public interface LaborRepository extends JpaRepository<Labor, Integer> {

    List<Labor> findByDescription(String description);

}
