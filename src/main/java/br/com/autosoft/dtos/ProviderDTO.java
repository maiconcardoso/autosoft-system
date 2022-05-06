package br.com.autosoft.dtos;

import javax.validation.constraints.NotEmpty;

import br.com.autosoft.entities.Provider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProviderDTO {

    private Integer id;

    @NotEmpty(message = "The name Provider cannot be empty")
    private String name;

    @NotEmpty(message = "The fone Provider cannot be empty")
    private String phoneNumber;
    private String cnpj;
    private String email;
    private String city;
    private String address;
    private String cep;

    public ProviderDTO(Provider provider) {
        this.id = provider.getId();
        this.name = provider.getName();
        this.phoneNumber = provider.getPhoneNumber();
        this.cnpj = provider.getCnpj();
        this.email = provider.getEmail();
        this.city = provider.getCity();
        this.address = provider.getAddress();
        this.cep = provider.getCep();
    }    
}
