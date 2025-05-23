package core;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private int id ;
    private String name ;
    private String Description ;
    private String slug ;
    static int idhelp=0 ;
    private List<Product> products = new ArrayList<>();
    private static List<Category> allCategories = new ArrayList<>();


    public Category(String name , String description){
        this.name=name ;
        this.Description = description;
        idhelp++;
        this.id=idhelp ;
        this.slug=Product.GenerateSlug(name) ;
    }
    public Category(){
        idhelp++;
        this.id=idhelp ;
    }
    public static void addToAllCategories(Category category){
        if(allCategories.contains(category)){
            System.out.println("Cette category est deja cree");
        }
        else{
            allCategories.add(category);
        }
    }
    public static void removeFromAllCategories(Category category){
        allCategories.remove(category);
    }
    public static void showAllCatgories(){
        if(allCategories.isEmpty()){
            System.out.println("Aucune category");
            return;
        }
        System.out.println("Les categories sont :");
        for(Category category:allCategories){
            System.out.println("- " +category.name);
        }
    }
    public static Category findCategoryByName(String name){
        for(Category category:allCategories){
            if(category.name.equalsIgnoreCase(name)){
                return category;
            }
        }
        return null ;
    }
    public static Category findCategoryById(int id ){
        for(Category category:allCategories){
            if(category.id==id){
                return category;
            }
        }
        return null;
    }


    public void addProduct(Product product){
        if(products.contains(product)){
            System.out.println("Ce produit appartient deja a cette category");
        }
        else{
            products.add(product);
            product.setCategory(this);
            System.out.println("Produit est ajoute a cette category");
        }
    }
    public void removeProduct(Product product){
        products.remove(product);
        product.setCategory(null);
    }
    public void showProducts(){
        if(products.isEmpty()){
            System.out.println("Aucun produit n'appartient a cette category");
            return;
        }
        System.out.println("La list des produit de la category :" +name);
        for(Product product: products){
            System.out.println(product);
        }
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
        if (!name.matches("(?i)delux|regular|busines")) {
            throw new IllegalArgumentException("Le nom peut contenir que des lettres");
        }
        this.name=name.toLowerCase() ;
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
    public void afficher(){
        System.out.println("Id du role : "+id);
        System.out.println("Nom :" +name );
        System.out.println("Description : " +Description);
        System.out.println("Slug : "+ slug);
    }
}
