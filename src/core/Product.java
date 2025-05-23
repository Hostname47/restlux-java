package core;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private int id ;
    private String title ;
    private String slug ;
    private double price ;
    private double discount ;
    private String tag;
    private Category category ;
    private int quantity ;
    private String imagePath ;
    private ProductManager PM;
    static int helpid=0 ;
    private List<Menu> menus = new ArrayList<>();
    private static List<Product> allProducts = new ArrayList<>();
    private List<Commande> commandes = new ArrayList<>();
    public Product(){
        helpid ++;
        id=helpid;
    }
    public Product(String title,String slug,int price, int discount,String tag,Category category ,int quantity){
        this.title = title;
        this.slug =slug;
        this.price=price;
        this.discount=discount;
        this.tag = tag ;
        this.category=category;
        this.quantity=quantity;
        helpid ++;
        id=helpid;
    }
    public void afficher(){
        System.out.println("Produit numero: "+id);
        System.out.println("titre produit: "+title);
        System.out.println("Slug : "+slug);
        System.out.println("prix: "+price);
        System.out.println("discount: "+discount);
        System.out.println("tag: "+tag);
        System.out.println("category: "+category);
        System.out.println("quantite: "+quantity);
        System.out.println("Chemin d'image: "+imagePath);
    }
    public String toString(){
        return  "Id du produit : "+id+
                "\nTitre :" +title +
                "\nSlug : "+ slug +
                "\nprix : "+price+
                "\ndiscount : "+discount+
                "\ntag : "+tag+
                "\ncategory : "+category+
                "\nquantite : "+quantity+
                "\nChemin d'image : "+ imagePath ;
    }
    public void addCommande(Commande commande){
        if(!commandes.contains(commande)){
            commandes.add(commande);
        }
    }
    public void removeCommande(Commande commande){
        commandes.remove(commande);
    }

    @Override // this one is used to compare hashmapKey.equals(product)  wen we use somth like map.get(product) --- default equals compare memory adress
    public boolean equals(Object obj) {
        if(this==obj) return true ;
        if(obj ==null || getClass()!=obj.getClass()) return false ;
        Product p = (Product) obj ;
        return this.title==p.title && this.id ==p.id;
    }

    @Override // this one comes first cuz we ll know the bucket we should search for (product.hashcode) then java will search in map for that bucket if it exist then use equals
    public int hashCode() {
        return Integer.hashCode(id); // return id ;
    }

    public static void addToAllProducts(Product product){
        if(allProducts.contains(product)){
            System.out.println("Ce produit existe deja");
        }
        else{
            allProducts.add(product);
    }}
    public static void removeFromAllPrducts(Product product){
        allProducts.remove(product);
    }
    public static void showAllProducts(){
        if(allProducts.isEmpty()){
            System.out.println("Aucun produit pour le moment");
        }
        else {
            for(Product product :allProducts){
                System.out.println(product);
            }
        }
    }
    public static Product findProductByTitle(String title){
        for(Product product :allProducts) {
            if(product.title.equalsIgnoreCase(title)){
                return product;
            }
        }
        return null;
    }

    public void addMenus(Menu menu){
        if(menus.contains(menu)){
            System.out.println(title+" appartient deja a "+menu.getName());
        }
        else{
            menus.add(menu);
            System.out.println("Menu contient ce produit");
            if(!menu.getProducts().contains(this)){
                menu.addProduct(this);
            }
        }
    }
    public void removeMenu(Menu menu){
        if(menus.remove(menu)){
            menu.removeProduct(this);
        }
    }
    public List<Menu> getMenus(){return menus;}
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSlug() {
        return slug;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscount() {
        return discount;
    }

    public String getTag() {
        return tag;
    }

    public Category getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public Boolean urExists(String urlstr){
        try{
            URL url =new URL(urlstr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection() ;// what i learned about downcasting
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(3000);
            connection.connect(); // for sending request (HEAD , it will wait 3000ms if the server dont answer it fails thrwo error)
            int code =connection.getResponseCode();
            return (code==200) ;//200 is successful code 404 not found
        }catch (Exception e){
            return false ;
        }
    }

    public void setImagePath(String pathOrurl){
        if(pathOrurl==null  || pathOrurl.trim().isEmpty()){
            throw new IllegalArgumentException("Le chemin ou l'URL de l'image est requis.");
        }
        pathOrurl = pathOrurl.trim();
        if(pathOrurl.startsWith("http://")  || pathOrurl.startsWith("https://")){ // here ik for sure that the object that will be returned from url.openconnection()is httpurl connc object that why i cast
            if(!urExists(pathOrurl)){
                throw new IllegalArgumentException("L'URL de l'image est invalide ou inaccessible.");
            }
            this.imagePath = pathOrurl ;
        }
        else{
            File file = new File(pathOrurl);
            if(!file.exists() || file.isDirectory()){
                throw new IllegalArgumentException("Fichier image invalide ou introuvable.");
            }
            this.imagePath = pathOrurl ;
        }
    }

    public void setTitle(String title) {
        if (title==null ||  title.trim().isEmpty()  ){
            throw new IllegalArgumentException("Titre requis.") ;
        }
        title = title.trim();
        if(!title.matches("^(?=(?:.*[A-Za-z]){4})[A-Za-z0-9 ]+$")){
            throw new IllegalArgumentException("Le titre doit contenir au moins 4 lettres et ne peut inclure que des lettres, des chiffres et des espaces. ");
        }
        this.title = title;
        this.slug=Product.GenerateSlug(title);
    }
// a methode to generate my slug same as i did in react
    public static String GenerateSlug(String input){
         return input.toLowerCase()
                .replaceAll("[^a-z0-9\\s]","") // means the patter replace everyth that is not a-z0-9 or white space with empty string
                .replaceAll("\\s+" , "-") ; // will replace one or many white spaces with -
    }

    public void setPrice(double price) {
        if(price <0){
            throw new IllegalArgumentException("Le prix ne peut pas être négatif.");
        }
        this.price = price;
    }

    public void setDiscount(double discount) {
        if (discount <0  || discount >100){
            throw new IllegalArgumentException("Le discount doit être comprise entre 0 et 100.");
        }
        this.discount = discount;
    }

    public void setTag(String tag) {
        if(tag==null  || tag.trim().isEmpty()){
            throw new IllegalArgumentException("Tag requis.");
        }
        tag = tag.trim();
        if(!tag.matches("^(?=(?:.*[A-Za-z]){4})[A-Za-z0-9 &'\\-]+$")){
            throw new IllegalArgumentException("Le tag doit contenir au moins 4 lettres et ne peut inclure que des lettres, des chiffres, des espaces, des esperluettes (&), des apostrophes (') et des tirets (-).");
        }
        this.tag = tag;
    }
    public void setCategory(Category category){
        this.category = category ;
    }
    public void setPM(ProductManager PM){
        this.PM = PM;
    }


    public void setQuantity(int quantity) {
            if (quantity <0){
                throw new IllegalArgumentException("La quantite ne peut pas être négatif .");
            }
        this.quantity = quantity;
    }
}
