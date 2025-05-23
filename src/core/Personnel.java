package core;

import java.sql.Timestamp;

public class Personnel extends User {
    public  Personnel( String name, String UsernameEmail, String password, Timestamp last_activity){
        // super(name,UsernameEmail,password,last_activity);
    }
    public Personnel(){

    }



    public void EffectuerTache(){

    }
    public void DemanderConge(){

    }
    public boolean VerifierDispoConge(){
        return false;
    }
}
