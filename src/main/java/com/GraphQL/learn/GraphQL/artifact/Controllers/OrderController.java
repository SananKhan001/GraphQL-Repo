package com.GraphQL.learn.GraphQL.artifact.Controllers;

import com.GraphQL.learn.GraphQL.artifact.Entities.Order;
import com.GraphQL.learn.GraphQL.artifact.Repository.UserRepo;
import com.GraphQL.learn.GraphQL.artifact.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserRepo userRepo;

    // createOrder(orderDetails:String,address:String,price:Int,userId:ID!):Order
    @MutationMapping
    public Order createOrder(@Argument String orderDetails, @Argument String address, @Argument int price, @Argument int userId){
        Order order = Order.builder()
                .orderDetails(orderDetails)
                .address(address)
                .price(price)
                .user(userRepo.findById(userId).get()).build();
        return orderService.createOrder(order);
    }

    // deleteOrder(orderId:ID!):Boolean
    @MutationMapping
    public boolean deleteOrder(@Argument int orderId){
        return orderService.deleteOrder(orderId);
    }

    // getOrder(orderId:ID!):Order
    @QueryMapping
    public Order getOrder(@Argument int orderId){
        return orderService.getOrder(orderId);
    }

    // getAllOrders:[Order]
    @QueryMapping
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

}
