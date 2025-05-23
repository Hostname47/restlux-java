package core;

import java.util.Date;

public class Reservation {
    private int id ;
    private Date date_reservation ;

    public  int getId(){
        return id ;
    }
    public Date getDate_reservation(){
        return date_reservation;
    }
    public void setId(int id ){
        this.id =id ;
    }

    public void setDate_reservation(Date date_reservation) {
        this.date_reservation = date_reservation;
    }

    public boolean EstAnnuler(){
        return  false ;
    }

}
