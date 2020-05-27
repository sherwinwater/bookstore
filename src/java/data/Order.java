package data;

import java.io.Serializable;

public class Order implements Serializable{
    
    private String orderID;
    private String invoiceID;
    private String username;

    public Order() {
    }

    public Order(String orderID, String invoiceID, String username) {
        this.orderID = orderID;
        this.invoiceID = invoiceID;
        this.username = username;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
}
