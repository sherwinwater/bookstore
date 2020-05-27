package data;

import java.io.Serializable;

public class Invoice implements Serializable {

    private String cart_id;
    private String invoice_id;

    public Invoice() {
    }

    public Invoice(String cart_id, String invoice_id) {
        this.cart_id = cart_id;
        this.invoice_id = invoice_id;
    }

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    public String getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(String invoice_id) {
        this.invoice_id = invoice_id;
    }

}
