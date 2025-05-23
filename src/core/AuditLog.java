package core;

import java.sql.Timestamp;


public class AuditLog {
    private int id ;
    private String action ;
    private Timestamp done_at ;
    private String Ip_adress ;
    private String Description ;

    public AuditLog(int id , String action,Timestamp done_at ,String Ip_adress , String Description){
        this.id =id;
        this.action=action;
        this.done_at= done_at;
        this.Ip_adress =Ip_adress;
        this.Description=Description ;
    }

    public int getId() {
        return id;
    }
    public String getAction(){
        return action ;
    }

    public Timestamp getDone_at() {
        return done_at;
    }

    public String getIp_adress() {
        return Ip_adress;
    }

    public String getDescription() {
        return Description;
    }
    public void setId(int id ){
        this.id=id ;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setDone_at(Timestamp done_at) {
        this.done_at = done_at;
    }

    public void setIp_adress(String ip_adress) {
        Ip_adress = ip_adress;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
