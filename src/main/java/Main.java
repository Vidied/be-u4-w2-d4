
import entities.Order;
import entities.Product;
import entities.Customer;

import java.time.LocalDate;
import java.util.List;

public class Main {
  public static void main(String[] args) {



    Customer c1 = new Customer();
    c1.setId(1L); c1.setName("Mario Rossi"); c1.setTier(1);

    Customer c2 = new Customer();
    c2.setId(2L); c2.setName("Luigi Verdi"); c2.setTier(2);

    Customer c3 = new Customer();
    c3.setId(3L); c3.setName("Anna Bianchi"); c3.setTier(2);



    Product p1 = new Product();
    p1.setId(101L); p1.setName("Il Signore degli Anelli"); p1.setCategory("Books"); p1.setPrice(120.0);

    Product p2 = new Product();
    p2.setId(102L); p2.setName("Java Guida Completa"); p2.setCategory("Books"); p2.setPrice(45.5);

    Product p3 = new Product();
    p3.setId(103L); p3.setName("Passeggino Leggero"); p3.setCategory("Baby"); p3.setPrice(150.0);

    Product p4 = new Product();
    p4.setId(104L); p4.setName("Biberon Anticolica"); p4.setCategory("Baby"); p4.setPrice(15.0);

    Product p5 = new Product();
    p5.setId(105L); p5.setName("T-Shirt Sportiva Boys"); p5.setCategory("Boys"); p5.setPrice(25.0);

    Product p6 = new Product();
    p6.setId(106L); p6.setName("Scarpe da ginnastica"); p6.setCategory("Boys"); p6.setPrice(80.0);



    List<Product> globalProducts = List.of(p1, p2, p3, p4, p5, p6);



    Order o1 = new Order(1L, "CONSEGNATO", LocalDate.of(2021, 1, 15), LocalDate.of(2021, 1, 20), List.of(p1, p3), c1);
    Order o2 = new Order(2L, "SPEDITO", LocalDate.of(2021, 2, 15), LocalDate.of(2021, 2, 18), List.of(p2, p5), c2);
    Order o3 = new Order(3L, "CONSEGNATO", LocalDate.of(2021, 5, 10), LocalDate.of(2021, 5, 15), List.of(p6), c3);
    Order o4 = new Order(4L, "IN ELABORAZIONE", LocalDate.of(2021, 3, 22), LocalDate.of(2021, 3, 25), List.of(p4), c2);



    List<Order> globalOrders = List.of(o1, o2, o3, o4);


    List<Product> booksFilter = globalProducts.stream().filter(product -> product.getPrice() > 100).filter(product -> product.getCategory().equals("Books")).toList();
    System.out.println("Prodotti della categoria Books");
    booksFilter.forEach(System.out::println);


    List<Order> babyOrder = globalOrders.stream()
        .filter(order -> order.getProducts().stream()
            .anyMatch(product -> product.getCategory().equals("Baby"))
    ).toList();

    System.out.println("\nOrdini contenenti un prodotto della categoria baby");
    babyOrder.forEach(System.out::println);


    List<Product> boysDiscount = globalProducts.stream().filter(product -> product.getCategory().equals("Boys"))
        .map(product -> {
          double prezzoDiscount = product.getPrice() * 0.9;
          product.setPrice(prezzoDiscount);
          return product;
        }).toList();

    System.out.println("\nProdotti della categoria boys con un prezzo scontato del 10%");
    boysDiscount.forEach(System.out::println);


    LocalDate dataInizio = LocalDate.of(2021, 2, 1);
    LocalDate dataFine = LocalDate.of(2021, 04, 1);

    List<Product> customerProductFilter = globalOrders.stream()
        .filter(order -> order.getCustomer().getTier().equals(2))
        .filter(order -> !order.getOrderDate().isBefore(dataInizio) && !order.getOrderDate().isAfter(dataFine))
        .flatMap(order -> order.getProducts().stream())
        .toList();

    System.out.println("\nProdotti ordinati dai clienti di tier 2 nella fascia di data specificata");
    customerProductFilter.forEach(System.out::println);




  }
}