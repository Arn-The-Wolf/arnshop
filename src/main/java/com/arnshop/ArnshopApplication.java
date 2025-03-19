package com.arnshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import com.arnshop.repositories.PersonRepository;
import com.arnshop.models.Order;
import com.arnshop.models.Person;
//import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ArnshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArnshopApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(PersonRepository repo) {
        return args -> {
            // Create a customer using builder
            Person customer = Person.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .shippingAddress("123 Main St, City, Country")
                .phoneNumber("+1234567890")
                .build();

            // Create an order using builder
            Order order = Order.builder()
                .customer(customer)
                .shippingAddress(customer.getShippingAddress())
                .paymentMethod("CREDIT_CARD")
                .build();

            // Add items to order
            order.addItem("Laptop", 999.99, 1);
            order.addItem("Mouse", 29.99, 2);

            // Add order to customer
            customer.getOrders().add(order);

            // Save customer (will cascade save the order)
            repo.save(customer);

            // Retrieve and display saved customers and their orders
            List<Person> savedPersons = repo.findAll();
            for (Person person : savedPersons) {
                System.out.printf("Customer ID: %d%n", person.getId());
                System.out.printf("Name: %s %s%n", person.getFirstName(), person.getLastName());
                System.out.printf("Email: %s%n", person.getEmail());
                System.out.printf("Phone: %s%n", person.getPhoneNumber());
                System.out.printf("Address: %s%n", person.getShippingAddress());
                System.out.printf("Number of orders: %d%n", person.getOrders().size());
                System.out.println("------------------------");
            }
        };
    }
}
