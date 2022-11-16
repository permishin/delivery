package com.example.deliveries.model;

import com.example.deliveries.entity.Product;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


public class CartModel {

    private final List<Product> products = new ArrayList<>();

    public void addItemProduct(Product product) {
        products.add(product);
    }

    public void deleteItemProduct(Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (product.getId().equals(products.get(i).getId())) {
                products.remove(i);
                break;
            }
        }
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public static CartModel getSession(HttpSession session) {
        CartModel cart = (CartModel) session.getAttribute("cart");
        if (cart == null) {
            cart = new CartModel();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    public void deleteAll(CartModel bean) {
        bean.products.clear();
    }

    public Boolean ifIdEqualsInCart(Long id) {
        for (int i = 0; i < getProducts().size(); i++) {
            if (getProducts().get(i).getId().equals(id)) {
                Integer c = getProducts().get(i).getCount() + 1;
                getProducts().get(i).setCount(c);
                return true;
            }
        }
        return false;
    }

    public Boolean idIsAddToCart(Long id) {
        for (int i = 0; i < getProducts().size(); i++) {
            if (getProducts().get(i).getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public Integer numberOfProduct(Long id) {
        for (int i = 0; i < getProducts().size(); i++) {
            if (getProducts().get(i).getId().equals(id)) {
                return i;
            }
        }
        return null;
    }
}

