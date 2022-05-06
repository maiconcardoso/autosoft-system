package br.com.autosoft.services;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.autosoft.dtos.ProviderDTO;
import br.com.autosoft.entities.Provider;
import br.com.autosoft.service.ProviderService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class ProviderServiceTest {

    @Autowired
    private ProviderService service;
    private Provider providerToBeSaved;
    private Provider providerSaved;

    @Mock
    private Pageable page;

    @BeforeEach
    public void setUp() {
        providerToBeSaved = createProvider();
        providerSaved = service.create(providerToBeSaved);
    }

    @Test
    public void mustSavedEntityProvider_whenSuccesfull() {
        Assertions.assertNotNull(providerSaved);
    }

    @Test
    public void mustReturnListProvider_whenSuccesfull() {
        List<ProviderDTO> list = service.readAll();
        log.info("Informações a respeito do retorno da Lista: " + list);
        Assertions.assertFalse(list.isEmpty());
    }

    @Test
    public void mustResturnProviderById_wheSuccesfull() {
        Integer id = providerSaved.getId();
        ProviderDTO providerById = service.readById(id);
        log.info("Informações a respeito do retorno do READBYID: " + providerById);
        Assertions.assertEquals(providerSaved.getId(), providerById.getId());
    }

    @Test
    public void mustReturnNameProvider_whenSuccesfull() {
        String name = providerSaved.getName();
        List<ProviderDTO> providerByName = service.readByName(name);
        Assertions.assertFalse(providerByName.isEmpty());
    }

    @Test
    public void mustUpdateProviderData_whenSuccesfull() {
        Integer id = providerSaved.getId();
        providerSaved.setName("Fornecedor 2");
        ProviderDTO providerForUpdated = service.update(id, providerSaved);
        Assertions.assertNotEquals(providerForUpdated.getName(), createProvider().getName());
        Assertions.assertEquals(providerForUpdated.getName(), "Fornecedor 2");
    }

    @Test
    public void mustReturnPageableProvider_whenSuccesfull() {
        Page<Provider> providerPage = service.readAllPageable(page);
        Assertions.assertFalse(providerPage.isEmpty());

    }

    public Provider createProvider() {
        return Provider.builder().id(1).name("Fornecedor").phoneNumber("34258855").email("fornecedor@email.com").cnpj("16.643.255/0001-02")
                .city("Paranavaí").cep("87025780").address("Rua quinze de novenbro, 559").build();
    }
}
