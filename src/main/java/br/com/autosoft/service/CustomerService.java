package br.com.autosoft.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.autosoft.dtos.CustomerDTO;
import br.com.autosoft.dtos.OrderDTO;
import br.com.autosoft.entities.Customer;
import br.com.autosoft.exceptions.EntityNotFoundException;
import br.com.autosoft.exceptions.NoSuchElementException;
import br.com.autosoft.repositories.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private OrderService orderService;

    public Page<Customer> readPageable(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<CustomerDTO> readAll() {
        List<Customer> registeredCustomer = repository.findAll();
        return registeredCustomer.stream().map(obj -> new CustomerDTO(obj)).collect(Collectors.toList());
    }

    public CustomerDTO readById(Integer id) {
        Optional<Customer> idCustomer = repository.findById(id);
        return idCustomer.stream().map(obj -> new CustomerDTO(obj)).findFirst()
                .orElseThrow(() -> new EntityNotFoundException(EntityNotFoundException.MESSAGE));
    }

    public List<CustomerDTO> readByName(String name) {
        List<Customer> customer = repository.findByName(name);
        return customer.stream().map(obj -> new CustomerDTO(obj)).collect(Collectors.toList());
    }

    public Customer save(Customer customerToBeSaved) {
        customerToBeSaved.setId(null);
        return repository.save(customerToBeSaved);
    }

    // public CustomerDTO save(Customer customerToBeSaved){
    //     Customer customerSaved = repository.save(customerToBeSaved);
    //     CustomerDTO newCustomer = new CustomerDTO(customerSaved);
    //     return newCustomer
    // }

    public CustomerDTO update(Customer customerToBeUpdated, Integer id) {
        Optional<Customer> customerById = repository.findById(id);
        if (customerById.isPresent()) {
            Customer customer = customerById.get();
            customer.setName(customerToBeUpdated.getName());
            customer.setPhoneNumber(customerToBeUpdated.getPhoneNumber());
            customer.setEmail(customerToBeUpdated.getEmail());
            customer.setCpf(customerToBeUpdated.getCpf());
            customer.setCity(customerToBeUpdated.getCity());
            customer.setCep(customerToBeUpdated.getCep());
            customer.setAddress(customerToBeUpdated.getAddress());
            repository.save(customer);
        }
        return customerById.stream().map(obj -> new CustomerDTO(obj)).findFirst()
            .orElseThrow(() -> new NoSuchElementException(NoSuchElementException.MESSAGE));
    }

    public void delete(Integer id) {
        Optional<OrderDTO> idCustomer = orderService.readByIdCustomer(id);
        if (idCustomer.isEmpty()) {
            repository.deleteById(id);
        }
    }
}
