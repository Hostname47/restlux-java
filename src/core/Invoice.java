package core;

import java.util.Date;

public class Invoice {
    private int id ;
    private String status ;
    private Date invoice_date ;
    private Date due_date ;

    public Invoice(int id ,String status , Date invoice_date , Date due_date){
        this.id =id ;
        this.status=status;
        this.invoice_date =invoice_date;
        this.due_date = due_date;
    }


    public int getId(){
        return id;
    }
    public String getStatus(){
        return status ;
    }
    public Date getInvoice_date(){
        return invoice_date ;
    }
    public Date getDue_date() {
        return due_date;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public void setInvoice_date(Date invoice_date) {
        this.invoice_date = invoice_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }
}
