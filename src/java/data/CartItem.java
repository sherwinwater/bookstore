/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author Sherwin
 */
public class CartItem extends Book {
    private int quantity;
    private double totalprice;
    private String cart_id;
    private String username;
    private int inventory;
    
    public CartItem(String id, Double price,String title,String author) {
        super(id, price,title,author);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalprice() {
        return this.totalprice = super.getPrice() * this.quantity;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }
    
}
