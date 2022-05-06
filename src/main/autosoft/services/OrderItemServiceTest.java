package br.com.autosoft.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.autosoft.entities.Order;
import br.com.autosoft.entities.OrderItem;
import br.com.autosoft.entities.Product;
import br.com.autosoft.service.OrderItemService;

@SpringBootTest
public class OrderItemServiceTest {

    @Autowired
    private OrderItemService service;

    OrderItem orderItemToBeSaved;
    OrderItem orderItem;

    
}
