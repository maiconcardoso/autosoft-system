package br.com.autosoft.services;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.autosoft.dtos.CustomerDTO;
import br.com.autosoft.entities.Customer;
import br.com.autosoft.service.CustomerService;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerService service;

    Customer customerToBeSaved;
    Customer customerSave;
    
    @Mock
    Pageable pageable;

    @BeforeEach
    public void setup(){
        customerToBeSaved = createCustomer();
        customerSave = service.save(customerToBeSaved);
    }

    @Test
    @DisplayName("Find by customer list when succesfull")
    public void mustSavedAndReturnCustomerList_whenSuccesfull() {
        List<CustomerDTO> listCustomer = service.readAll(); 
        Assertions.assertFalse(listCustomer.isEmpty());
    }

    @Test
    @DisplayName("Find by id Customer when succesfull")
    public void mustSavedAndReturnIdCustomer_whenSuccesfull() {
        Integer id = customerSave.getId();
        CustomerDTO customerById = service.readById(id);
        Assertions.assertEquals(customerSave.getId(), customerById.getId());
    }

    @Test
    @DisplayName("Must find by name customer when succesfull")
    public void mustReturnNameCustomer_whenSuccesfull() {
        List<CustomerDTO> customerByName = service.readByName(customerSave.getName());
        Assertions.assertFalse(customerByName.isEmpty());
    }

    @Test
    @DisplayName("Must Update customer when succesfull")
    public void mustUpdateCustomer_whenSuccesfull() {
        CustomerDTO customerForUpdate = service.update(customerSave, customerSave.getId());
        customerForUpdate.setName("NewCliente");
        Assertions.assertNotEquals(customerForUpdate.getName(), customerToBeSaved.getName());
        Assertions.assertEquals(customerForUpdate.getName(), "NewCliente");
        Assertions.assertEquals(customerSave.getName(), "Cliente");
    }

    
    @Test
    @DisplayName("Must Delete customer when succesfull")
    public void mustDeleteCustomer_whenSuccesfull() {
        CustomerDTO customerById = service.readById(customerSave.getId());
        Integer id = customerById.getId();
        service.delete(id);
        Assertions.assertNotNull(id);
    }

    @Test
    @DisplayName("Must return customer pageable when succesfull")
    public void mustReturnPageableCustomer_whenSuccesfull() {
        Page<Customer> customerPage = service.readPageable(pageable);
        Assertions.assertFalse(customerPage.isEmpty());
    }

    public Customer createCustomer() {
        return Customer.builder().id(1).name("Cliente").phoneNumber("991682996").email("cliente@email.com").cpf("870754885")
                .city("Cidade").cep("87075325").address("Rua do cliente").build();
    }
}
