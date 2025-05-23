package core;

import javax.sound.sampled.Port;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Menu {
    private int id ;
    private String name;
    private String description ;
    private Date date_dispo ;
    private Date date_exp ;
    private ProductManager PM;
    private static int idHelp=0;
    private List<Product> products = new ArrayList<>();
    private static List<Menu> allMenus = new ArrayList<>();


    public Menu ( String name ,String description,Date date_dispo , Date date_exp){
        this.name = name ;
        this.description = description;
        this.date_dispo = date_dispo ;
        this.date_exp = date_exp;
        idHelp++;
        id=idHelp;
    }
    public Menu(){
        idHelp++;
        id=idHelp;
    }
    public static void addToAllMenus(Menu menu){
        if(allMenus.contains(menu)){
            System.out.println("Ce menu deja exist");
        }
        else{
            allMenus.add(menu);
        }
    }
    public static void removeFromAllMenus(Menu menu){
        allMenus.remove(menu);
    }
    public static Menu findMenuByName(String name){
        for(Menu menu:allMenus){
            if(menu.name.equalsIgnoreCase(name)){
                return menu;
            }
        }
        return null;
    }




    public void addProduct(Product product){
        if(products.contains(product)){
            System.out.println("Ce produit existe deja dans ce menu");
        }
        else{
            products.add(product);
            System.out.println("Produit ajoute au menu");
            if(!product.getMenus().contains(this)){
                product.addMenus(this);
            }
        }
    }
    public void removeProduct(Product product){
        if(products.remove(product)){
            product.removeMenu(this);
        }

    }
    public int getId(){
        return id ;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public Date getDate_dispo(){
        return date_dispo;
    }
    public Date getDate_exp(){
        return date_exp ;
    }
    public List<Product> getProducts(){return products;}
    public void setId(int id ){
        this.id =id ;
    }
    public void setPM(ProductManager pm){
        this.PM =pm;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate_dispo(Date date_dispo) {
        this.date_dispo = date_dispo;
    }

    public void setDate_exp(Date date_exp) {
        this.date_exp = date_exp;
    }
}
