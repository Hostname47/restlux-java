package core;

public class Abonnement {
    private int id ;
    private String Type_abonnement ;

    public Abonnement(int id ,String Type_abonnement){
        this.id=id ;
        this.Type_abonnement=Type_abonnement;
    }
    public int getId(){
        return id ;
    }
    public String getType_abonnement(){
        return Type_abonnement ;
    }
    public void setId(int id ){
        this.id =id ;
    }
    public void setType_abonnement(String Type_abonnement){
        this.Type_abonnement =Type_abonnement;
    }


    public boolean EstExpirer(){
        return false ;
    }



}
