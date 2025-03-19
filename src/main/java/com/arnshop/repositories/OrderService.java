package com.arnshop.services;

import com.arnshop.models.Order;

import com.arnshop.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElse(null);
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order orderDetails) {
        Order order = getOrderById(id);
        if (order != null) {
            order.setOrderNumber(orderDetails.getOrderNumber());
            order.setTotalAmount(orderDetails.getTotalAmount());
            order.setStatus(orderDetails.getStatus());
            order.setShippingAddress(orderDetails.getShippingAddress());
            order.setPaymentMethod(orderDetails.getPaymentMethod());
            order.setPaymentStatus(orderDetails.getPaymentStatus());
            return orderRepository.save(order);
        }
        return null;
    }

    public boolean deleteOrder(Long id) {
        Order order = getOrderById(id);
        if (order != null) {
            orderRepository.delete(order);
            return true;
        }
        return false;
    }
}