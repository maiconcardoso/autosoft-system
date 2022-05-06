package br.com.autosoft.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.autosoft.entities.OrderLabor;

@Repository
public interface OrderLaborRepository extends JpaRepository<OrderLabor, Integer>{

    //SELECT obj FROM OrderItem obj WHERE obj.product.id LIKE :id_product
    @Query("SELECT obj FROM OrderLabor obj WHERE obj.labor.id LIKE :id_labor")
    List<OrderLabor> findByIdLabor(Integer id_labor);
    
}
