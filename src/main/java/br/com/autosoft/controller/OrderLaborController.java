package br.com.autosoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import br.com.autosoft.dtos.OrderLaborDTO;
import br.com.autosoft.entities.OrderLabor;
import br.com.autosoft.service.OrderLaborService;

@CrossOrigin(origins = "https://autosoft-system.firebaseapp.com")
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("v1/order-labors")
public class OrderLaborController {
    
    @Autowired
    private OrderLaborService service;

    @GetMapping
    public ResponseEntity<List<OrderLaborDTO>> findAll() {
        List<OrderLaborDTO> orderLaborList = service.readAll();
        return ResponseEntity.status(HttpStatus.OK).body(orderLaborList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderLaborDTO> findById(@PathVariable Integer id) {
        OrderLaborDTO orderLaborById = service.readById(id);
        return ResponseEntity.status(HttpStatus.OK).body(orderLaborById);
    }

    @GetMapping("/labor")
    public ResponseEntity<List<OrderLaborDTO>> findByIdLabor(@RequestParam Integer id) {
        List<OrderLaborDTO> orderLaborsByIdLabor = service.readByIdLabor(id);
        return ResponseEntity.status(HttpStatus.OK).body(orderLaborsByIdLabor);
    }

    @PostMapping
    public ResponseEntity<OrderLaborDTO> save(@RequestBody OrderLabor orderLabor, 
    @RequestParam(value = "order", defaultValue = "0") Integer id_order) {
        OrderLaborDTO orderLaborToBeSaved = service.create(orderLabor, id_order);
        return ResponseEntity.status(HttpStatus.OK).body(orderLaborToBeSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderLaborDTO> update(@PathVariable Integer id, @RequestBody OrderLabor orderLabor) {
        OrderLaborDTO orderLaborToBeUpdated = service.update(id, orderLabor);
        return ResponseEntity.status(HttpStatus.OK).body(orderLaborToBeUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
