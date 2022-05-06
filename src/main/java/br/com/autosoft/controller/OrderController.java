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

import br.com.autosoft.dtos.OrderDTO;
import br.com.autosoft.entities.Order;
import br.com.autosoft.service.OrderService;

@CrossOrigin(origins = "https://autosoft-system.firebaseapp.com")
@RestController
@RequestMapping("v1/orders")
public class OrderController {

    @Autowired
    OrderService service;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAll() {
        List<OrderDTO> orderList = service.readAll();
        return ResponseEntity.status(HttpStatus.OK).body(orderList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable Integer id) {
        OrderDTO orderById = service.readById(id);
        return ResponseEntity.status(HttpStatus.OK).body(orderById);
    }

    @GetMapping("/find")
    public ResponseEntity<List<OrderDTO>> findByCustomerName(@RequestParam String name) {
        List<OrderDTO> orderByNameCustomer = service.readByNameCustomer(name);
        return ResponseEntity.status(HttpStatus.OK).body(orderByNameCustomer);
    }

    @PostMapping
    public ResponseEntity<OrderDTO> save(@RequestBody Order order) {
        OrderDTO newOrder = service.save(order);
        return ResponseEntity.status(HttpStatus.OK).body(newOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> update(@RequestBody Order order, @PathVariable Integer id) {
        OrderDTO orderForUpdate = service.update(order, id);
        return ResponseEntity.status(HttpStatus.OK).body(orderForUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderDTO> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
