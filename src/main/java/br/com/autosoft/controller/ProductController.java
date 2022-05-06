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

import br.com.autosoft.dtos.ProductDTO;
import br.com.autosoft.entities.Product;
import br.com.autosoft.service.ProductService;

@CrossOrigin(origins = "https://autosoft-system.firebaseapp.com")
@RestController
@RequestMapping("v1/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product productToBeSaved) {
        Product product = service.create(productToBeSaved);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Integer id) {
        ProductDTO productById = service.readById(id);
        return ResponseEntity.status(HttpStatus.OK).body(productById);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() {
        List<ProductDTO> productList = service.readAll();
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

    @GetMapping("/find")
    public ResponseEntity<List<ProductDTO>> findByName(@RequestParam String name) {
        List<ProductDTO> productByName = service.readByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(productByName);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Integer id, @RequestBody Product product) {
        ProductDTO productToBeUpdate = service.update(id, product);
        return ResponseEntity.status(HttpStatus.OK).body(productToBeUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
}
