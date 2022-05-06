package br.com.autosoft.controllers;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.autosoft.controller.CustomerController;
import br.com.autosoft.dtos.CustomerDTO;
import br.com.autosoft.entities.Customer;

@SpringBootTest
public class CustomerControllerTest {

    @Mock
    private Pageable page;

    @Autowired
    private CustomerController controller;

    Customer customerToBeSaved;
    ResponseEntity<Customer> customerSave;

    public Customer createCustomer() {
        return Customer.builder().id(1).name("Cliente").phoneNumber("991682996").email("cliente@email.com").cpf("870754885")
                .city("Cidade").cep("87075325").address("Rua do cliente").build();
    }

    @BeforeEach
    public void setup() {
        customerToBeSaved = createCustomer();
        customerSave = controller.save(customerToBeSaved);
    }

    @Test
    public void mustReturnHttpStatusCreatedForCustomer_whenSuccesfull() {
        Assertions.assertEquals(customerSave.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void mustReturnHttpStatusOkForListCustomer_whenSuccesfull() {
        ResponseEntity<List<CustomerDTO>> listCustomer = controller.findAll();
        Assertions.assertEquals(listCustomer.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void musReturnHttpStatusOkForIdCustomer_whenSuccesfull() {
        Integer id = customerToBeSaved.getId();
        ResponseEntity<CustomerDTO> customerById = controller.findById(id);
        Assertions.assertEquals(customerById.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void mustReturnHttpStatusOkForPageCustomer_whenSuccesfull() {
        ResponseEntity<Page<Customer>> customerPageable = controller.findPageable(page);
        Assertions.assertEquals(customerPageable.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void mustReturnHttpStatusOkForFindByNameCustomer_whenSuccesfull() {
        String name = createCustomer().getName();
        ResponseEntity<List<CustomerDTO>> customerByName = controller.findByName(name);
        Assertions.assertEquals(customerByName.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals("Cliente", name);
    }

    @Test
    public void mustReturnHttpStatusOkForUpdateCustomer_whenSuccesfull() {
        Integer id = createCustomer().getId();
        Customer customerToBeUpdated = createCustomer();
        customerToBeUpdated.setEmail("email@modificado.com");
        ResponseEntity<CustomerDTO> customerUpdate = controller.update(customerToBeUpdated, id);
        Assertions.assertEquals(customerUpdate.getStatusCode(), HttpStatus.OK);
        Assertions.assertNotEquals(customerToBeUpdated.getEmail(), createCustomer().getEmail());
    } 
}
