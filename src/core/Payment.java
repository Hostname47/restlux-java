package core;

import java.util.Date;

public class Payment {
    private int id ;
    private String payment_methode ;
    private double amount ;
    private Date paid_at ;
    private String bank_name ;


    public Payment(int id , String payment_methode,double amount ,Date paid_at , String bank_name){
        this.id =id ;
        this.payment_methode=payment_methode;
        this.amount = amount;
        this.paid_at = paid_at ;
        this.bank_name=bank_name ;
    }

    public int getId() {
        return id;
    }

    public String getPayment_methode() {
        return payment_methode;
    }

    public double getAmount() {
        return amount;
    }

    public Date getPaid_at() {
        return paid_at;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPayment_methode(String payment_methode) {
        this.payment_methode = payment_methode;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPaid_at(Date paid_at) {
        this.paid_at = paid_at;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }
}
