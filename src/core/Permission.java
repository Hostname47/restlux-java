package core;

import java.util.ArrayList;
import java.util.List;

public class Permission {
    private int id;
    private String name ;
    private String Description ;
    private String slug ;
    private static int idhelp=0;
    private List<Role> roles = new ArrayList<>();
    private List<User> users= new ArrayList<>();
    private static List<Permission> allPermissions = new ArrayList<>();

    public Permission(String name,String Description,String slug){
        this.name = name ;
        this.Description =Description;
        idhelp++ ;
        id=idhelp;
    }
    public Permission(){
        idhelp++ ;
        id=idhelp;
    }
    public static void addToAllPermissions(Permission permission){
        if(!allPermissions.contains(permission)){
            allPermissions.add(permission);
        }
    }

    public static void showAllPermissions(){
        for(Permission permission:allPermissions){
            System.out.println(permission);
        }
    }
    public static void removeFromAllPermissions(String permissionName){
        for(Permission permission:allPermissions){
            if(permission.getName().equalsIgnoreCase(permissionName)){
                allPermissions.remove(permission);
            }
        }
    }



    public void addUser(User user){
        if(users.contains(user)){
            System.out.println("Cette permission est deja donne a " +user.getFullname());
        }
        else{
            users.add(user);
            System.out.println("User admet cette permission "+getName());
//            if(!user.getPermissions().contains(this)){
//                user.addPermission(this);
//            }
        }
    }
    public void removeUser(User user){
        if(users.remove(user)){
            // user.removePermission(this);
        }
    }
    public void showUsers(){
        if(users.isEmpty()){
            System.out.println("Aucun user avec cette permission");
            return;
        }
        System.out.println("Les users admettant cette permission "+name+" sont: ");
        for(User user : users){
            System.out.println("- "+user);
        }
    }
    public void addRole(Role role){
        if(roles.contains(role)){
            System.out.println("Ce role est deja ajoute");
        }
        else {
            roles.add(role);
            System.out.println(role.getName()+ " admet la permission "+this.getName()+role.getName());
            if(!role.getPermissions().contains(this)){
                // role.addPermission(this);
            }
        }
    }
    public void removeRole(Role role){
        if(roles.remove(role)){
            // role.removePermission(this);
        }
    }
    public void showRoles(){
        if(roles.isEmpty()){
            System.out.println("Aucun role admet la permission: "+this.name);
        }
        System.out.println("Role avec cette permission: " + name + " :\n");
        for (Role role : roles) {
            System.out.println("- " + role.getName());
        }
    }
    public List<User> getUsers(){return users ;}
    public List<Role> getRoles(){return roles ;}
    public int getId(){
        return id ;
    }
    public String getName(){
        return name ;
    }
    public String getDescription(){
        return Description ;
    }
    public String getSlug(){
        return slug ;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name ){
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Nom est requis");
        }
        name = name.trim();
        if (!name.matches("^[A-Z][A-Z0-9_]{5,}$")) {
            throw new IllegalArgumentException("Le nom doit être en majuscules, commencer par une lettre et avoir au moins 5 caractères.");
        }
        this.name=name ;
        // this.slug =Product.GenerateSlug(name);
    }
    public void setDescription(String description) {
        if(description ==null || description.trim().isEmpty() ){
            throw new IllegalArgumentException("La description est requise");
        }
        description = description.trim();
        if(!description.matches("^[\\w\\s.,'\"()\\-]{10,255}$")){
            throw new IllegalArgumentException("La description doit contenir au min 10 carcteres ");
        }
        this.Description = description;
    }
    public String toString(){
        return "Id de la permission : "+id+
                "\nNom :" +name +
                "\nDescription : "+Description+
                "\nSlug : "+ slug ;
    }
}
