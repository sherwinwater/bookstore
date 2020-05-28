package data;

import java.io.Serializable;

public class CreditCard implements Serializable{
    
    private String id;
    private String firstname;
    private String lastname;
    private String cardtype;
    private String cardnumber;
    private String expirationdate;


    public CreditCard() {
    }

    public CreditCard(String id, String firstname, String lastname, String cardtype, String cardnumber, String expirationdate) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.cardtype = cardtype;
        this.cardnumber = cardnumber;
        this.expirationdate = expirationdate;
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getExpirationdate() {
        return expirationdate;
    }

    public void setExpirationdate(String expirationdate) {
        this.expirationdate = expirationdate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

   
}
