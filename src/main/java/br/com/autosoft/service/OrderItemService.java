package br.com.autosoft.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.autosoft.dtos.OrderItemDTO;
import br.com.autosoft.entities.Order;
import br.com.autosoft.entities.OrderItem;
import br.com.autosoft.exceptions.EntityNotFoundException;
import br.com.autosoft.exceptions.NoSuchElementException;
import br.com.autosoft.repositories.OrderItemRepository;
import br.com.autosoft.repositories.OrderRepository;

@Service
public class OrderItemService {

    @Autowired
    OrderItemRepository repository;

    @Autowired
    OrderRepository orderRepository;

    public List<OrderItemDTO> readAll() {
        List<OrderItem> order = repository.findAll();
        return order.stream().map(obj -> new OrderItemDTO(obj)).collect(Collectors.toList());
    }

    // public OrderItemDTO readById(Integer id) {
    //     Optional<OrderItem> orderItemById = repository.findById(id);
    //     return orderItemById.stream().map((obj) -> new OrderItemDTO(obj)).findFirst()
    //             .orElseThrow(() -> new NoSuchElementException(NoSuchElementException.MESSAGE));
    // }

    public OrderItemDTO readById(Integer id) {
        OrderItem orderItemById = repository.findById(id).get();
        OrderItemDTO orderItemByIdDTO = new OrderItemDTO(orderItemById);
        try {
            return orderItemByIdDTO;
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(NoSuchElementException.MESSAGE);
        }
    }

    public List<OrderItemDTO> readByIdProduct(Integer id_product) {
        List<OrderItem> orderItemByIdProduct = repository.findByIdProduct(id_product);
        return orderItemByIdProduct.stream().map(obj -> new OrderItemDTO(obj)).collect(Collectors.toList());
    }

    public OrderItemDTO create(OrderItem orderItem, Integer id_order) {
        Order order = orderRepository.findById(id_order).get();
        orderItem.setOrder(order);
        OrderItem orderItemForSaved = repository.save(orderItem);
        OrderItemDTO orderItemSaved = new OrderItemDTO(orderItemForSaved);
        return orderItemSaved;
    }

    // public OrderItemDTO update(Integer id, OrderItem orderItem) {
    //     Optional<OrderItem> orderItemById = repository.findById(id);
    //     if (orderItemById.isPresent()) {
    //         OrderItem orderItemForToUpdated = orderItemById.get();
    //         orderItemForToUpdated.setOrder(orderItem.getOrder());
    //         orderItemForToUpdated.setProduct(orderItem.getProduct());
    //         orderItemForToUpdated.setSubTotal(orderItem.getSubTotal());
    //         orderItemForToUpdated.setQuantity(orderItem.getQuantity());
    //         repository.save(orderItemForToUpdated);
    //     }
    //     return orderItemById.map((obj) -> new OrderItemDTO(obj))
    //             .orElseThrow(() -> new EntityNotFoundException(EntityNotFoundException.MESSAGE));
    // }

    public OrderItemDTO update(Integer id, OrderItem orderItem) {
        OrderItem orderItemById = repository.findById(id).get();
        if (orderItemById != null) {
            OrderItem orderItemForToUpdated = orderItemById;
            orderItemForToUpdated.setOrder(orderItem.getOrder());
            orderItemForToUpdated.setProduct(orderItem.getProduct());
            orderItemForToUpdated.setSubTotal(orderItem.getSubTotal());
            orderItemForToUpdated.setQuantity(orderItem.getQuantity());
            repository.save(orderItemForToUpdated);
        }
        OrderItemDTO orderItemByIdDTO = new OrderItemDTO(orderItemById);
        try {
            return orderItemByIdDTO;
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(EntityNotFoundException.MESSAGE);
        }
    }

    public void delete(Integer id) {
        readById(id);
        repository.deleteById(id);
    }
}
