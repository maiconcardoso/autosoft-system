package br.com.autosoft.services;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.autosoft.dtos.ProductDTO;
import br.com.autosoft.entities.Product;
import br.com.autosoft.service.ProductService;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService service;

    Product productToBeSaved;
    Product productSaved;

    private Product createProduct() {
        return Product.builder().id(1).name("Produto criado").factoryCode("BG0654").brand("Mahle")
                .groupFamily("groupFamily")
                .subGroup("Motor AP").application("gasolina").price(254.4).build();
    }

    @BeforeEach
    public void setup() {
        productToBeSaved = createProduct();
        productSaved = service.create(productToBeSaved);
    }

    @Test
    public void mustCreateEntityProduct() {
        Assertions.assertNotNull(productSaved);
        Assertions.assertEquals(productSaved.getName(), "Produto criado");
    }

    @Test
    public void mustReturnEntityProductById() {
        Integer id = productSaved.getId();
        ProductDTO product = service.readById(id);
        Assertions.assertEquals(product.getId(), productSaved.getId());
    }

    @Test
    public void mustReturnListEntityProduct() {
        List<ProductDTO> listProduct = service.readAll();
        Assertions.assertNotNull(listProduct);
        Assertions.assertTrue(!listProduct.isEmpty());
    }

    @Test
    public void mustReturnNameProductEntity() {
        String name = productSaved.getName();
        List<ProductDTO> productName = service.readByName(name);
        Assertions.assertFalse(productName.isEmpty());
    }

    @Test
    public void mustUpdateProductEntity() {
        Integer id = productSaved.getId();
        productSaved.setName("Produto Atualizado");
        ProductDTO productToBeUpdated = service.update(id, productSaved);
        Assertions.assertNotEquals(productToBeUpdated.getName(), createProduct().getName());
    }
}
