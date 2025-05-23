package core;

import security.Permission;

import java.util.ArrayList;
import java.util.List;

public class Role {
    private int id ;
    private String name ;
    private String Description ;
    private String slug ;
    static int idhelp=0 ;
    private List<User> users=new ArrayList<>();
    private static List<Role> allRoles = new ArrayList<>(); // here i put Roles that admin will create
    private List<Permission> permissions= new ArrayList<>();

    public Role(String name,String Description){
        this.name = name ;
        this.Description =Description;
        this.slug = Product.GenerateSlug(name) ;
        idhelp++ ;
        this.id=idhelp;
        addToAllRoles(this);
    }
    public Role(){
        idhelp++ ;
        id=idhelp;
        addToAllRoles(this);
    }
    public void addPermission(Permission permission){
        if(permissions.contains(permission)){
            System.out.println("Cette permission est deja creer");
        }
        else {
            permissions.add(permission);
//            System.out.println("La permission "+permission.getName() +" est ajoute au role" +this.name);
//            if(!permission.getRoles().contains(this)){
//                permission.addRole(this);
//            }
        }
    }
    public void removePermission(Permission permission){
        if(permissions.remove(permission)){
            // permission.removeRole(this);
        }
    }
    public void showPermissions(){
        if(permissions.isEmpty()){
            System.out.println("Aucune permission pour le role: "+this.name);
        }
        System.out.println("Permission pour le rôle: " + name + " :\n");
        for (Permission permission : permissions) {
            // System.out.println("- " + permission.getName());
        }
    }
    public boolean hasPermission(String permissionName){
        for(Permission permission: permissions){
//            if(permission.getName().equalsIgnoreCase(permissionName)){
//                return true ;
//            }
        }
        return false ;
    }


    public static void addToAllRoles(Role role){
        if(allRoles.contains(role)){
            System.out.println("Ce role est deja creer");
        }
        else {
            allRoles.add(role);
        }
    }
    public static void removeFromAllRoles(String roleName){
        for(Role role :allRoles) {
            if (role.getName().equalsIgnoreCase(roleName)) {
                allRoles.remove(role);
            }
        }



    }
    public static void showAllRoles(){
        if(allRoles.isEmpty()){
            System.out.println("La liste des roles est vide ");
            return;
        }
        System.out.println("La liste de tous les roles: ");
            for(Role role :allRoles){
                System.out.println("- " + role);
            }
    }
    public static Role findRoleByName(String roleName){
        for(Role role: allRoles){
            if(role.name.equalsIgnoreCase(roleName)){
                return role ;
            }
        }
        return null ;
    }
    public void addUser(User user){
        if(users.contains(user)){
            System.out.println("Cet utilisateur a déjà ce rôle");
        }
        else{
            users.add(user);
//            System.out.println(user.getName() +" est ajouté au role : "+this.getName());
//            if(!user.getRoles().contains(this)){
//                user.addRole(this);
//            }

        }
    }
    public void removeUser(User user){
        if(users.remove(user)){
            // user.removeRole(this);
        }
    }
    public void showUsers(){
        if(users.isEmpty()) {
            System.out.println("Aucun utilisateur avec le rôle " + name);
            return;
        }
        System.out.println("Utilisateurs avec le rôle " + name + " :\n");
        for (User user : users) {
            // System.out.println("- " + user.getName());
        }
    }
    public List<Permission> getPermissions(){return permissions;}
    public List<User> getUsers(){ return users ;}
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
        if (!name.matches("(?i)^(client|client manager|product manager|admin|director|worker)$")) {
            throw new IllegalArgumentException("Le nom peut contenir que des lettres");
        }
        this.name=name ;
        this.slug =Product.GenerateSlug(name);
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
        return "Id du role : "+id+
                "\nNom :" +name +
                "\nDescription : "+Description+
                "\nSlug : "+ slug ;
    }
}
