package org.example;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;

public class Main 
{
    private static List<Product> productList = new ArrayList<>();
    private static List<Customer> customerList = new ArrayList<>();
    private static List<Order> orderList = new ArrayList<>();

    public static Faker faker = new Faker(new Locale("it-IT"));

    public static void main( String[] args ) {
        generateProducts();
        generateCustomers();
        generateOrders();

        // esercizio1
        Map<Customer, List<Order>> ordersForCustomer = orderList.stream().collect(Collectors.groupingBy(Order::getCustomer));
        ordersForCustomer.forEach((key, value) -> System.out.println("Customer: " + key + "Orders: " + value));


    }

    public static void generateProducts(){
        Product p1 = new Product(1, faker.commerce().productName(), "Books", 100);
        Product p2 = new Product(2, faker.commerce().productName(), "Books", 146);
        Product p3 = new Product(3, faker.commerce().productName(), "Books", 38);
        Product p4 = new Product(4, faker.commerce().productName(), "Boys", 12);
        Product p5 = new Product(5, faker.commerce().productName(), "Boys", 176);
        Product p6 = new Product(6, faker.commerce().productName(), "Boys", 29);
        Product p7 = new Product(7, faker.commerce().productName(), "Baby", 36);
        Product p8 = new Product(8, faker.commerce().productName(), "Baby", 276);
        Product p9 = new Product(9, faker.commerce().productName(), "Baby", 10);

        productList.addAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9));
    }

    public static void generateCustomers(){
        Customer c1 = new Customer(1, faker.name().fullName(), 1);
        Customer c2 = new Customer(2, faker.name().fullName(), 2);
        Customer c3 = new Customer(3, faker.name().fullName(), 3);
        Customer c4 = new Customer(4, faker.name().fullName(), 2);

        customerList.addAll(Arrays.asList(c1, c2, c3, c4));
    }

    public static void generateOrders(){
        Order o1 = new Order(1, customerList.get(0));
        Order o2 = new Order(1, customerList.get(1));
        Order o3 = new Order(1, customerList.get(2));
        Order o4 = new Order(1, customerList.get(3));
        Order o5 = new Order(1, customerList.get(3));
        Order o6 = new Order(1, customerList.get(1));
        Order o7 = new Order(1, customerList.get(2));
        Order o8 = new Order(1, customerList.get(0));
        Order o9 = new Order(1, customerList.get(2));
        Order o10 = new Order(1, customerList.get(1));
        Order o11 = new Order(1, customerList.get(1));
        Order o12 = new Order(1, customerList.get(3));

        orderList.addAll(Arrays.asList(o1, o2, o3, o4));
    }
}
