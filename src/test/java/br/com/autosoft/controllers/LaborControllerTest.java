package br.com.autosoft.controllers;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.autosoft.controller.LaborController;
import br.com.autosoft.dtos.LaborDTO;
import br.com.autosoft.entities.Labor;

@SpringBootTest
public class LaborControllerTest {

    @Autowired
    private LaborController controller;

    private Labor laborToBeSaved;
    private ResponseEntity<Labor> laborSaved;

    public Labor createLabor() {
        return Labor.builder().id(1).description("Descrição do Serviço").price(45.8).groupFamily("Grupo")
                .subGroup("subGroup").build();
    }

    @BeforeEach
    public void setup() {
        laborToBeSaved = createLabor();
        laborSaved = controller.save(laborToBeSaved);
    }

    @Test
    public void mustReturnHttpStatusCreateForEntityLabor() {
        Assertions.assertEquals(laborSaved.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void mustReturHttpOKStatusForLaborById() {
        Integer id = laborToBeSaved.getId();
        ResponseEntity<LaborDTO> laborById = controller.findById(id);
        Assertions.assertEquals(laborById.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void mustReturnHttpStatusOkForLaborByDescription() {
        String description = laborToBeSaved.getDescription();
        ResponseEntity<List<LaborDTO>> laborByDescription = controller.findByDescription(description);
        Assertions.assertEquals(laborByDescription.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void mustReturHttpStatusOKForLaborUpdate() {
        Integer id = laborToBeSaved.getId();
        ResponseEntity<LaborDTO> laborToBeUpdate = controller.update(id, laborToBeSaved);
        Assertions.assertEquals(laborToBeUpdate.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void mustReturnHttpStatusOkForFindAllLabor() {
        ResponseEntity<List<LaborDTO>> laborList = controller.findAll();
        Assertions.assertEquals(laborList.getStatusCode(), HttpStatus.OK);
    }

}
