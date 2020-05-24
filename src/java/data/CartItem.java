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
    
}
