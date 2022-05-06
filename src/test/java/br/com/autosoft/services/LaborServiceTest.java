package br.com.autosoft.services;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.autosoft.dtos.LaborDTO;
import br.com.autosoft.entities.Labor;
import br.com.autosoft.service.LaborService;

@SpringBootTest
public class LaborServiceTest {

    @Autowired
    private LaborService service;

    private Labor laborToBeSaved;
    private Labor laborSaved;

    public Labor createLabor() {
        return Labor.builder().description("Descrição do Serviço").price(45.8).groupFamily("Grupo")
                .subGroup("subGroup").application("gasolina").build();
    }

    @BeforeEach
    public void setup() {
        laborToBeSaved = createLabor();
        laborSaved = service.create(laborToBeSaved);
    }

    @Test
    public void mustReturnTrueSavedEntityLabor() {
        Assertions.assertEquals(laborSaved.getId(), laborToBeSaved.getId());
    }

    @Test
    public void mustReturnLaborById() {
        Integer id = laborSaved.getId();
        LaborDTO laborById = service.readById(id);
        Assertions.assertNotNull(laborById);
    }

    @Test
    public void mustReturnListLabor_whenSuccesfull() {
        List<LaborDTO> laborList = service.readAll();
        Assertions.assertFalse(laborList.isEmpty());
    }

    @Test
    public void mustReturnListEntityLaborByDescription_whenSuccesfull() {
        String description = laborSaved.getDescription();
        List<LaborDTO> laborByDescriptionList = service.readByDescription(description);
        Assertions.assertTrue(!laborByDescriptionList.isEmpty());
    }

    @Test
    public void mustUpdateTheEntityLabor_whenSuccesfull() {
        Integer id = laborSaved.getId();
        laborSaved.setDescription("Nova Description");
        LaborDTO laborToBeUpdated = service.update(id, laborSaved);
        Assertions.assertEquals(laborToBeUpdated.getDescription(), laborToBeSaved.getDescription());
    }

}
