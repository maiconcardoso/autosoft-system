package br.com.autosoft.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.autosoft.dtos.ProductDTO;
import br.com.autosoft.entities.Product;
import br.com.autosoft.exceptions.EntityNotFoundException;
import br.com.autosoft.exceptions.NoSuchElementException;
import br.com.autosoft.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product create(Product productToBeSaved) {
        return repository.save(productToBeSaved);
    }

    public ProductDTO readById(Integer id) {
        Optional<Product> productById = repository.findById(id);
        return productById.stream().map((obj) -> new ProductDTO(obj)).findFirst()
                .orElseThrow(() -> new EntityNotFoundException(EntityNotFoundException.MESSAGE));
    }

    public List<ProductDTO> readAll() {
        List<Product> listProduct = repository.findAll();
        return listProduct.stream().map((obj) -> new ProductDTO(obj)).collect(Collectors.toList());
    }

    public List<ProductDTO> readByName(String name) {
        List<Product> productByName = repository.findByName(name);
        return productByName.stream().map((obj) -> new ProductDTO(obj)).collect(Collectors.toList());
    }

    public ProductDTO update(Integer id, Product product) {
        Optional<Product> productById = repository.findById(id);
        if (productById.isPresent()) {
            Product productToBeUpdate = productById.get();
            productToBeUpdate.setName(product.getName());
            //productToBeUpdate.setProvider(product.getProvider());
            productToBeUpdate.setFactoryCode(product.getFactoryCode());
            productToBeUpdate.setGroupFamily(product.getGroupFamily());
            productToBeUpdate.setSubGroup(product.getSubGroup());
            productToBeUpdate.setApplication(product.getApplication());
            productToBeUpdate.setPrice(product.getPrice());
            productToBeUpdate.setBrand(product.getBrand());
            repository.save(productToBeUpdate);
        }
        return productById.stream().map(obj -> new ProductDTO(obj)).findFirst()
                .orElseThrow(() -> new NoSuchElementException(NoSuchElementException.MESSAGE));
    }

    public void delete(Integer id) {
        readById(id);
        repository.deleteById(id);
    }
}
