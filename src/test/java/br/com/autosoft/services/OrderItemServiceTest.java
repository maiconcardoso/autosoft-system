package br.com.autosoft.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.autosoft.entities.OrderItem;
import br.com.autosoft.service.OrderItemService;

@SpringBootTest
public class OrderItemServiceTest {

    @Autowired
    private OrderItemService service;

    OrderItem orderItemToBeSaved;
    OrderItem orderItem;

    
}
