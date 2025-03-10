package com.ikea.fuzzy.repo;

import com.ikea.fuzzy.dtos.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepo {

    private List<Product> products = new ArrayList<>();

    public ProductRepo() {
        products.add(new Product(1L, "Dog", "Animal", "Hairy and has four legs", 123, "https://picsum.photos/200/300"));
        products.add(new Product(2L, "Barbie", "Toy", "A doll", 123, "https://picsum.photos/200/300"));
        products.add(new Product(3L, "Pizza", "Food", "Pepperoni, cheese and mushrooms", 123, "https://picsum.photos/200/300"));
        products.add(new Product(4L, "Cat", "Animal", "Small, furry, and has a tail", 100, "https://picsum.photos/200/300"));
        products.add(new Product(5L, "Laptop", "Electronics", "Portable computer", 1200, "https://picsum.photos/200/300"));
        products.add(new Product(6L, "Watch", "Accessories", "Tells time", 250, "https://picsum.photos/200/300"));
        products.add(new Product(7L, "Table", "Furniture", "Made of wood, used for dining", 500, "https://picsum.photos/200/300"));
        products.add(new Product(8L, "Shoe", "Apparel", "Protects your feet", 80, "https://picsum.photos/200/300"));
        products.add(new Product(9L, "Phone", "Electronics", "Used for communication", 800, "https://picsum.photos/200/300"));
        products.add(new Product(10L, "T-shirt", "Apparel", "Casual clothing", 25, "https://picsum.photos/200/300"));
        products.add(new Product(11L, "Headphones", "Electronics", "Used for listening to music", 150, "https://picsum.photos/200/300"));
        products.add(new Product(12L, "Bed", "Furniture", "Where you sleep", 300, "https://picsum.photos/200/300"));
        products.add(new Product(13L, "Fridge", "Appliance", "Keeps food cold", 600, "https://picsum.photos/200/300"));
        products.add(new Product(14L, "Book", "Stationery", "A written or printed work", 20, "https://picsum.photos/200/300"));
        products.add(new Product(15L, "Camera", "Electronics", "Captures images", 500, "https://picsum.photos/200/300"));
        products.add(new Product(16L, "Shoes", "Apparel", "For walking or running", 100, "https://picsum.photos/200/300"));
        products.add(new Product(17L, "Car", "Vehicle", "Used for transportation", 15000, "https://picsum.photos/200/300"));
        products.add(new Product(18L, "Keyboard", "Electronics", "Used for typing", 50, "https://picsum.photos/200/300"));
        products.add(new Product(19L, "Pillow", "Furniture", "For resting your head", 40, "https://picsum.photos/200/300"));
        products.add(new Product(20L, "Lamp", "Furniture", "Provides light", 30, "https://picsum.photos/200/300"));
        products.add(new Product(21L, "Socks", "Apparel", "Keeps your feet warm", 10, "https://picsum.photos/200/300"));
        products.add(new Product(22L, "Microwave", "Appliance", "Heats food", 120, "https://picsum.photos/200/300"));
        products.add(new Product(23L, "Charger", "Accessories", "Used to charge electronic devices", 15, "https://picsum.photos/200/300"));
        products.add(new Product(24L, "Gloves", "Apparel", "Protects your hands from cold", 20, "https://picsum.photos/200/300"));
        products.add(new Product(25L, "Towel", "Household", "Used for drying yourself", 5, "https://picsum.photos/200/300"));
        products.add(new Product(26L, "Couch", "Furniture", "For sitting or reclining", 350, "https://picsum.photos/200/300"));
        products.add(new Product(27L, "Umbrella", "Accessories", "Protects from rain", 15, "https://picsum.photos/200/300"));
        products.add(new Product(28L, "Backpack", "Accessories", "Used for carrying items", 40, "https://picsum.photos/200/300"));
        products.add(new Product(29L, "Fan", "Appliance", "Used for circulating air", 50, "https://picsum.photos/200/300"));
        products.add(new Product(30L, "Rug", "Furniture", "Decorative piece for the floor", 75, "https://picsum.photos/200/300"));
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product addProduct(Product product) {
        products.add(product);
        return product;
    }

    public Optional<Product> getProductById(long id) {
        return products.stream().filter(p -> p.id() == id).findFirst();
    }
}
