package br.com.autosoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.autosoft.dtos.OrderItemDTO;
import br.com.autosoft.entities.OrderItem;
import br.com.autosoft.service.OrderItemService;

@RestController
@CrossOrigin(origins = "https://autosoft-system.firebaseapp.com")
@RequestMapping("v1/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemService service;

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDTO> findById(@PathVariable Integer id) {
        OrderItemDTO orderItemById = service.readById(id);
        return ResponseEntity.status(HttpStatus.OK).body(orderItemById);
    }

    @GetMapping
    public ResponseEntity<List<OrderItemDTO>> findAll() {
        List<OrderItemDTO> orders = service.readAll();
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @GetMapping("/product")
    public ResponseEntity<List<OrderItemDTO>> findByIdProduct(@RequestParam Integer id) {
        List<OrderItemDTO> orderItemByIdProduct = service.readByIdProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(orderItemByIdProduct);
    }   

    @PostMapping
    public ResponseEntity<OrderItemDTO> save(@RequestBody OrderItem orderItem,
            @RequestParam(value = "order", defaultValue = "0") Integer id_order) {
        OrderItemDTO orderItemSaved = service.create(orderItem, id_order);
        return ResponseEntity.status(HttpStatus.OK).body(orderItemSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemDTO> update(@PathVariable Integer id, @RequestBody OrderItem orderItem) {
        OrderItemDTO orderItemUpdated = service.update(id, orderItem);
        return ResponseEntity.status(HttpStatus.OK).body(orderItemUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
