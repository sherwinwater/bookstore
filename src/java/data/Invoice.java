package data;

import java.io.Serializable;

public class Invoice implements Serializable {

    private String cart_id;
    private String invoice_id;
    private String creditcard_id;
    private String contact_id;

    public Invoice() {
    }

    public Invoice(String cart_id, String invoice_id, String creditcard_id, String contact_id) {
        this.cart_id = cart_id;
        this.invoice_id = invoice_id;
        this.creditcard_id = creditcard_id;
        this.contact_id = contact_id;
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

    public String getCreditcard_id() {
        return creditcard_id;
    }

    public void setCreditcard_id(String creditcard_id) {
        this.creditcard_id = creditcard_id;
    }

    public String getContact_id() {
        return contact_id;
    }

    public void setContact_id(String contact_id) {
        this.contact_id = contact_id;
    }

    

}
