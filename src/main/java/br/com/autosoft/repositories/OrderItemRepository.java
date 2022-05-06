package br.com.autosoft.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.autosoft.entities.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{

    @Query("SELECT obj FROM OrderItem obj WHERE obj.product.id LIKE :id_product")
    List<OrderItem> findByIdProduct(Integer id_product);
    
}
