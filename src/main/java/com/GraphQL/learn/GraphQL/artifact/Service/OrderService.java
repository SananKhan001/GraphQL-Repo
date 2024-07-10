package com.GraphQL.learn.GraphQL.artifact.Service;

import com.GraphQL.learn.GraphQL.artifact.Entities.Order;
import com.GraphQL.learn.GraphQL.artifact.Repository.OrderRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    public Order createOrder(Order order){
        return orderRepo.save(order);
    }

    public List<Order> getAllOrders(){
        return orderRepo.findAll();
    }

    public Order getOrder(int orderId){
        return orderRepo.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Specified Order is not Present !!!"));
    }

    public boolean deleteOrder(int orderId){
        orderRepo.deleteById(orderId);
        return true;
    }

}
