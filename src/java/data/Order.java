package data;

import java.io.Serializable;

public class Order implements Serializable{
    
    private String orderID;
    private String invoiceID;
    private boolean isPackaged;
    private boolean isDelivered;

    public Order() {
    }

    public Order(String orderID, String invoiceID) {
        this.orderID = orderID;
        this.invoiceID = invoiceID;
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

    public boolean isIsPackaged() {
        return isPackaged;
    }

    public void setIsPackaged(boolean isPackaged) {
        this.isPackaged = isPackaged;
    }

    public boolean isIsDelivered() {
        return isDelivered;
    }

    public void setIsDelivered(boolean isDelivered) {
        this.isDelivered = isDelivered;
    }


    
    
}
