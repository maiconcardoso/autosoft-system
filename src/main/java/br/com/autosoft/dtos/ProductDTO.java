package br.com.autosoft.dtos;

import br.com.autosoft.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDTO {
    
    private Integer id;
    private String name;
    private String factoryCode;
    //private Provider provider;
    private String groupFamily;
    private String subGroup;
    private String application;
    private Double price;
    private String brand;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.factoryCode = product.getFactoryCode();
        //this.provider = product.getProvider();
        this.groupFamily = product.getGroupFamily();
        this.subGroup = product.getSubGroup();
        this.application = product.getApplication();
        this.price = product.getPrice();
        this.brand = product.getBrand();
    }

}
