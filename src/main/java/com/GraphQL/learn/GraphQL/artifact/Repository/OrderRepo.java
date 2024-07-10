package com.GraphQL.learn.GraphQL.artifact.Repository;

import com.GraphQL.learn.GraphQL.artifact.Entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
}
