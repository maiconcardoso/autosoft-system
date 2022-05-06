package br.com.autosoft.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.autosoft.dtos.OrderDTO;
import br.com.autosoft.entities.Order;
import br.com.autosoft.exceptions.EntityNotFoundException;
import br.com.autosoft.exceptions.NoSuchElementException;
import br.com.autosoft.repositories.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Transactional(readOnly = true)
    public List<OrderDTO> readAll() {
        List<Order> orderList = repository.findAll();
        return orderList.stream().map(obj -> new OrderDTO(obj)).collect(Collectors.toList());
    }

    // public OrderDTO readById(Integer id) {
    //     Optional<Order> orderById = repository.findById(id);
    //     return orderById.stream().map(obj -> new OrderDTO(obj)).findFirst()
    //             .orElseThrow(() -> new EntityNotFoundException(EntityNotFoundException.MESSAGE));
    // }

    public OrderDTO readById(Integer id) {
        Order orderById = repository.findById(id).get();
        OrderDTO orderByIdDTO = new OrderDTO(orderById);
        try {
            return orderByIdDTO;
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(EntityNotFoundException.MESSAGE);
        }
    }

    public List<OrderDTO> readByNameCustomer(String name) {
        List<Order> orderByNameCustomer = repository.findByCustomerName(name);
        return orderByNameCustomer.stream().map((obj) -> new OrderDTO(obj)).collect(Collectors.toList());
    }

    // public Optional<OrderDTO> readByIdCustomer(Integer id) {
    //     Optional<Order> orderByIdCustomer = repository.findByCustomerId(id);
    //     return orderByIdCustomer.stream().map(obj -> new OrderDTO(obj)).findFirst();
    // }

    public OrderDTO readByIdCustomer(Integer id) {
        Order orderByIdCustomer = repository.findByCustomerId(id).get();
        OrderDTO orderByIdCustomerDTO = new OrderDTO(orderByIdCustomer);
        try {
            return orderByIdCustomerDTO;
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(NoSuchElementException.MESSAGE);
        }
    }

    public OrderDTO save(Order order) {
        Order orderToBySaved = repository.save(order);
        OrderDTO newOrder = new OrderDTO(orderToBySaved);
        return newOrder;
    }

    // public OrderDTO update(Order order, Integer id) {
    //     Optional<Order> orderById = repository.findById(id);
    //     if (orderById.isPresent()) {
    //         Order orderForUpdate = orderById.get();
    //         orderForUpdate.setStatus(order.getStatus());
    //         orderForUpdate.setCreationDate(order.getCreationDate());
    //         orderForUpdate.setCustomer(order.getCustomer());
    //         repository.save(order);
    //     }
    //     return orderById.stream().map(obj -> new OrderDTO(obj)).findFirst()
    //             .orElseThrow(() -> new NoSuchElementException(NoSuchElementException.MESSAGE));
    // }

    public OrderDTO update(Order order, Integer id) {
        Order orderById = repository.findById(id).get();
        if (orderById != null) {
            Order orderForUpdate = orderById;
            orderForUpdate.setStatus(order.getStatus());
            orderForUpdate.setCreationDate(order.getCreationDate());
            orderForUpdate.setCustomer(order.getCustomer());
            repository.save(order);
        }
        OrderDTO orderByIdDTO = new OrderDTO(orderById);
        try {
            return orderByIdDTO;
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(NoSuchElementException.MESSAGE);
        }
    }

    // public void delete(Integer id) {
    //     Optional<Order> orderById = repository.findById(id);
    //     if (!orderById.isPresent())
    //         new NoSuchElementException(NoSuchElementException.MESSAGE);
    //     repository.deleteById(id);
    // }

    public void delete(Integer id) {
        Order orderById = repository.findById(id).get();
        if ((orderById == null))
            new NoSuchElementException(NoSuchElementException.MESSAGE);
        repository.deleteById(id);
    }
}
