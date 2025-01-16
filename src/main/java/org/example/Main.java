package org.example;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static List<Product> productList = new ArrayList<>();
    private static List<Customer> customerList = new ArrayList<>();
    private static List<Order> orderList = new ArrayList<>();

    public static Faker faker = new Faker(new Locale("it-IT"));

    public static void main(String[] args) {
        generateProducts();
        generateCustomers();
        generateOrders();

        // esercizio1
        System.out.println("---------esercizio1----------");
        Map<Customer, List<Order>> ordersForCustomer = orderList.stream().collect(Collectors.groupingBy(Order::getCustomer));
        ordersForCustomer.forEach((customer, order) -> System.out.println("Customer: " + customer.getName() + ", Number of orders: " + order.size()));
        System.out.println("");

        // esercizio2
        System.out.println("---------esercizio2----------");
        Map<Customer, Double> totalOrders = getOrderByCustomerTotal();
        totalOrders.forEach((customer, total) -> {
            System.out.println("Customer: " + customer.getName() + ", Total: " + total + "€");
        });
        System.out.println("");

        // esercizio 3
        System.out.println("---------esercizio3----------");
        getMoreExpansive().forEach(product -> System.out.println("Il prodotto " + product.getName() + " ha prezzo " + product.getPrice() + "€"));
        System.out.println("");

        // esercizio 4
        System.out.println("---------esercizio4----------");
        System.out.println("La media degli ordini è " + getAverageOrders() + "€");
        System.out.println("");

        // esercizio 5
        System.out.println("---------esercizio5----------");
        getTotalCategories().forEach((category, price) -> System.out.println("La categoria è " + category + " e la somma degli importi è " + price + "€"));
        System.out.println("");
    }

    public static void generateProducts() {
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

    public static void generateCustomers() {
        Customer c1 = new Customer(1, faker.name().fullName(), 1);
        Customer c2 = new Customer(2, faker.name().fullName(), 2);
        Customer c3 = new Customer(3, faker.name().fullName(), 3);
        Customer c4 = new Customer(4, faker.name().fullName(), 2);

        customerList.addAll(Arrays.asList(c1, c2, c3, c4));
    }

    public static void generateOrders() {
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

        Product p1 = productList.get(0);
        Product p2 = productList.get(1);
        Product p3 = productList.get(2);
        Product p4 = productList.get(3);
        Product p5 = productList.get(4);
        Product p6 = productList.get(5);
        Product p7 = productList.get(6);
        Product p8 = productList.get(7);
        Product p9 = productList.get(8);

        o1.addProduct(p1);
        o1.addProduct(p4);

        o2.addProduct(p6);

        o3.addProduct(p5);
        o3.addProduct(p8);

        o4.addProduct(p3);
        o4.addProduct(p1);
        o4.addProduct(p7);

        o5.addProduct(p1);

        o6.addProduct(p9);

        o7.addProduct(p8);

        o8.addProduct(p2);

        o9.addProduct(p3);

        o10.addProduct(p7);

        o11.addProduct(p4);

        o12.addProduct(p5);
        o12.addProduct(p1);

        orderList.addAll(Arrays.asList(o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12));
    }


    // metodo 2
    public static Map<Customer, Double> getOrderByCustomerTotal() {
        return orderList.stream().collect(Collectors.groupingBy(Order::getCustomer, Collectors.summingDouble(Order::getTotal)));
    }

    // metodo 3
    public static List<Product> getMoreExpansive() {
        return productList.stream().sorted(Comparator.comparing(Product::getPrice).reversed()).limit(3).toList();
    }

    // metodo 4
    public static double getAverageOrders(){
        return orderList.stream().collect(Collectors.averagingDouble(Order::getTotal));
    }

    //metodo 5
    public static Map<String, Double> getTotalCategories(){
        return productList.stream().collect(Collectors.groupingBy(Product::getCategory, Collectors.summingDouble(Product::getPrice)));
    }
}
