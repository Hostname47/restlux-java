package core;

import java.util.Date;

public class Session {
    private int id ;
    private String token ;
    private Date created_at ;
    private Date expired_at ;
    private User user ;
    private static int idhelp;

    public Session(String token,Date created_at,Date expired_at ,User user){
        this.token= token;
        this.created_at = created_at;
        this.expired_at= expired_at;
        this.user=user;
        idhelp++;
        id=idhelp;
    }
    public Session(){
        idhelp++;
        id=idhelp;
    }

    public int getId() {
        return id;
    }
    public String getToken(){
        return token ;
    }
    public Date getCreated_at(){
        return created_at ;
    }
    public Date getExpired_at(){
        return expired_at;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setExpired_at(Date expired_at) {
        this.expired_at = expired_at;
    }

}
