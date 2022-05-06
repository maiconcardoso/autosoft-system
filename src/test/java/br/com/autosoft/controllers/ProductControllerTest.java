package br.com.autosoft.controllers;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.autosoft.controller.ProductController;
import br.com.autosoft.dtos.ProductDTO;
import br.com.autosoft.entities.Product;
import br.com.autosoft.entities.Provider;

@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private ProductController controller;

    Product productToBeSaved;
    ResponseEntity<Product> productSaved;

    private Product createProduct() {
        return Product.builder().id(1).name("Produto criado").factoryCode("BG0654").brand("Mahle")
                .groupFamily("groupFamily")
                .subGroup("Motor AP").price(254.4).build();
    }

    @BeforeEach
    public void setup() {
        productToBeSaved = createProduct();
        productSaved = controller.save(productToBeSaved);
    }

    @Test
    public void mustReturnResponseEntityCreateForProductSaved() {
        Assertions.assertEquals(productSaved.getStatusCodeValue(), HttpStatus.CREATED.value());
    }

    @Test
    public void mustReturnResponseEntityOkForProductById() {
        Integer id = productToBeSaved.getId();
        ResponseEntity<ProductDTO> productById = controller.findById(id);
        Assertions.assertEquals(productById.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void mustReturResponseEntityOkForProductFindAll() {
        ResponseEntity<List<ProductDTO>> productList = controller.findAll();
        Assertions.assertEquals(productList.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void mustReturnResponseEntityOkForProductByName() {
        String name = productToBeSaved.getName();
        ResponseEntity<List<ProductDTO>> productByName = controller.findByName(name);
        Assertions.assertEquals(productByName.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void mustReturnResponseEntityOkForProductUpdate() {
        Integer id = productToBeSaved.getId();
        productToBeSaved.setName("Novo produto");
        ResponseEntity<ProductDTO> productUpdate = controller.update(id, productToBeSaved);
        Assertions.assertEquals(productUpdate.getStatusCode(), HttpStatus.OK);
    }
}
