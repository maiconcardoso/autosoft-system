package br.com.autosoft.controllers;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.autosoft.controller.ProviderController;
import br.com.autosoft.dtos.ProviderDTO;
import br.com.autosoft.entities.Provider;

@SpringBootTest
public class ProviderControllerTest {

    @Mock
    private Pageable page;

    @Autowired
    private ProviderController controller;

    private Provider providerToBeSaved;
    ResponseEntity<Provider> providerSave;

    public Provider createProvider() {
        return Provider.builder().id(1).name("Fornecedor").phoneNumber("34258855").email("fornecedor@email.com").cnpj("16.643.255/0001-02")
                .city("Paranavaí").cep("87025780").address("Rua quinze de novenbro, 559").build();
    }

    @BeforeEach
    public void setup() {
        providerToBeSaved = createProvider();
        providerSave = controller.save(providerToBeSaved);
    }

    @Test
    public void mustReturnHttpStatusCreateForSaveProvider_whenSuccesfull() {
        Assertions.assertEquals(providerSave.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void mustReturnHttpStatusOkForFindAllProvider_whenSuccesfull() {
        ResponseEntity<List<ProviderDTO>> providerList = controller.findAll();
        Assertions.assertEquals(providerList.getStatusCode(), HttpStatus.OK);
    }

    @Test 
    public void mustReturnHttpStatusOkForFindByIdProvider_whenSuccesfull() {
        Integer id = providerToBeSaved.getId();
        ResponseEntity<ProviderDTO> providerById = controller.findById(id);
        Assertions.assertEquals(providerById.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void mustReturHttpStatusOkForUpdateProvider_whenSuccesfull() {
        Integer id = providerToBeSaved.getId();
        ResponseEntity<ProviderDTO> providerForUpdated = controller.update(id, providerToBeSaved);
        Assertions.assertEquals(providerForUpdated.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void mustReturnHttpStatusOkForFindByNameProvider_whenSuccesfull() {
        String name = providerToBeSaved.getName();
        ResponseEntity<List<ProviderDTO>> providerByName = controller.findByName(name);
        Assertions.assertEquals(providerByName.getStatusCode(), HttpStatus.OK);
    }
    
}
