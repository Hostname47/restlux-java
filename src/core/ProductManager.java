package core;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductManager extends Personnel{

 private List<Product> products;
 private List<Menu> menus = new ArrayList<>();

 public ProductManager(String name, String usernameEmail, String password, Timestamp last_activity){
    super(name,usernameEmail,password,last_activity);
    products = new ArrayList<>() ;
 }
 public ProductManager(){

 }

   Scanner sc =new Scanner(System.in);
    public void createMenu(){
        Menu menu= new Menu();

    }
    public void addMeu(Menu menu){
        if(menus.contains(menu)){
            System.out.println("Menu existe deja");
        }
        else{
            menus.add(menu);
            menu.setPM(this);
        }
    }
    public void removeMenu(Menu menu){
        menus.remove(menu);
        menu.setPM(null);
    }
    public void createCategory(){
        Category category = new Category();
        while(true){
            System.out.println("Entrer le nom de la category");
            String input =sc.nextLine().trim();
            try{
                category.setName(input);
                break;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
        while(true){
            System.out.println("Entrer une description");
            String input = sc.nextLine().trim();
            try{
                category.setDescription(input);
                break ;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
        Category.addToAllCategories(category);
        System.out.println("Category ajouté avec succès !");

    }
    public void modififerCategory(){
        Category.showAllCatgories();
        System.out.println("Veuillez choisir la categorie que vous voulez modifier");
        String input = sc.nextLine().trim();
        Category category = Category.findCategoryByName(input);
        if(category==null){
            System.out.println("Cette categorie n'existe pas");
        }
        else {
            mainLoop:
            while(true){
                System.out.println(category);
                System.out.println("Choisr ce que vous voulez changer");
                System.out.println("1)name");
                System.out.println("2)description");
                String answer=sc.nextLine().trim();
                switch (answer){
                    case"1":   while(true){
                               System.out.println("Entrer le nouveau nom de la category");
                               String newCategory =sc.nextLine().trim();
                               try{
                                   category.setName(newCategory);
                                   break;
                               }catch (IllegalArgumentException e){
                                   System.out.println(e.getMessage());
                               }
                    }break ;
                    case "2":  while(true){
                               System.out.println("Entrer une nouvelle description");
                               String newDescription = sc.nextLine().trim();
                               try{
                                   category.setDescription(newDescription);
                                   break ;
                               }catch (IllegalArgumentException e){
                                   System.out.println(e.getMessage());
                               }
                    }break;
                    default:System.out.println("Choix invalid,ressayer");
                            continue mainLoop;
                }
                while(true){
                    System.out.println("Voulez vous changer une autre chose pour cette categorie? (yes/no)");
                    String repeat =sc.nextLine().toLowerCase().trim();
                    switch (repeat){
                        case "yes": continue mainLoop;
                        case "no": break mainLoop;
                        default:continue;
                    }
                }
            }
        }
    }
    public void removeCategory(){
        Category.showAllCatgories();
        System.out.println("Entrer le nom de la categorie que vous voulez supprimer");
        String input = sc.nextLine().trim();
        Category category = Category.findCategoryByName(input);
        if(category==null){
            System.out.println("Cette categorie n'existe pas");
        }
        else{
            Category.removeFromAllCategories(category);
            System.out.println("Category supprimee avec succès !");
        }
    }



   public void createProduct(){
    Product p = new Product();

    while (true){
        System.out.println("Entrer titre du produit");
        String title =sc.nextLine().trim();
        try{
            p.setTitle(title);
            break;
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
    while(true){
        System.out.println("Entrer le prix du produit");
        String input =sc.nextLine().trim();
        try{
        if(input.isEmpty()) throw new IllegalArgumentException("Prix est requis.") ;
        double price= Double.parseDouble(input) ; // this might throw exception
        p.setPrice(price);
        break;
        }catch (NumberFormatException e){
            System.out.println("Entrez un nombre valide.");
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
    while(true){
        System.out.println("Entrer le **chemin local** de l'image ou son **URL**: ");
        String pathName = sc.nextLine().trim();
        try{
            p.setImagePath(pathName);
            break;
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
    while(true){
        System.out.println("Entrer le discount du produit entre 1 et 100%");
        String input = sc.nextLine().trim();

        try {
            if(input.isEmpty()) throw new IllegalArgumentException("Discount est requis");
            Double discount = Double.parseDouble(input);
            p.setDiscount(discount);
            break;
        }catch (NumberFormatException e){
            System.out.println("Entrez un nombre valide.");
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
    while(true){
        System.out.println("Entrer le tag du produit");
        String tag =sc.nextLine().trim();
        try {
            p.setTag(tag);
            break;
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
    while(true){
        System.out.println("Entrer la category du produit");
        String categoryName=sc.nextLine().trim();
        Category category = Category.findCategoryByName(categoryName);
        if(category==null){
            System.out.println("Cette categorie n'existe pas ");
        }
        else{
            p.setCategory(category);
            break;
        }
    }
    while(true){
        System.out.println("Entrer la quantite du produit");
        String input =sc.nextLine().trim();
        try{
            if(input.isEmpty()) throw new IllegalArgumentException("Qantite est requise");
            int quantity = Integer.parseInt(input);
            p.setQuantity(quantity);
            break;
        }catch (NumberFormatException e){
            System.out.println("Entrez un entier valide.");
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
       Product.addToAllProducts(p);
       System.out.println("Produit ajouté avec succès !");
    }









    public void modifierProduit(){
        Product.showAllProducts();
        System.out.println("Entrer le titre du produit que vous voulez modifier :");
        String titleInput = sc.nextLine().trim();
        Product p =Product.findProductByTitle(titleInput);
        if(p ==null){
            System.out.println("Ce produit n'existe pas");
        }
        else {
            mainLoop:
            while(true){
                System.out.println("\nQue voulez-vous modifier ?");
                System.out.println("1) Titre");
                System.out.println("2) Prix");
                System.out.println("3) Discount");
                System.out.println("4) Tag");
                System.out.println("5) Catégorie");
                System.out.println("6) Quantité");
                System.out.println("7) Chemin d'image");

                String input = sc.nextLine().trim();
                switch (input){
                    case "1":   while (true){
                        System.out.println("Nouveau titre :");
                        String title =sc.nextLine().trim();
                        try{
                            p.setTitle(title);
                            System.out.println("Titre modifié !");
                            break;
                        }catch (IllegalArgumentException e){
                            System.out.println(e.getMessage());
                        }
                    }break;
                    case "2":   while(true){
                        System.out.println("Nouveau prix :");
                        String pricestr =sc.nextLine().trim();
                        try{
                            if(input.isEmpty()) throw new IllegalArgumentException("Prix est requis.") ;
                            double price= Double.parseDouble(pricestr) ; // this might throw exception
                            p.setPrice(price);
                            System.out.println("Prix modifié !");
                            break;
                        }catch (NumberFormatException e){
                            System.out.println("Entrez un nombre valide.");
                        }
                        catch (IllegalArgumentException e){
                            System.out.println(e.getMessage());
                        }
                    }break;
                    case "3":    while(true){
                        System.out.println("Nouveau discount :");
                        String discountstr = sc.nextLine().trim();
                        try {
                            if(input.isEmpty()) throw new IllegalArgumentException("Discount est requis");
                            Double discount = Double.parseDouble(discountstr);
                            p.setDiscount(discount);
                            System.out.println("Discount modifié !");
                            break;
                        }catch (NumberFormatException e){
                            System.out.println("Entrez un nombre valide.");
                        }
                        catch (IllegalArgumentException e){
                            System.out.println(e.getMessage());
                        }
                    }break;
                    case "4":   while(true){
                        System.out.println("Nouveau tag :");
                        String tag =sc.nextLine().trim();
                        try {
                            p.setTag(tag);
                            System.out.println("Tag modifié !");
                            break;
                        }catch (IllegalArgumentException e){
                            System.out.println(e.getMessage());
                        }
                    }break;
                    case "5":   while(true){
                        System.out.println("Entrer la nouvelle categorie");
                        String categoryName=sc.nextLine().trim();
                        Category category = Category.findCategoryByName(categoryName);
                        if(category==null){
                            System.out.println("Cette categorie n'existe pas ");
                        }
                        else{
                            p.setCategory(category);
                            break;
                        }
                    }break;
                    case "6":   while(true){
                        System.out.println("Nouvelle quantite");
                        String quantitystr =sc.nextLine().trim();
                        try{
                            if(input.isEmpty()) throw new IllegalArgumentException("Qantite est requise");
                            int quantity = Integer.parseInt(quantitystr);
                            p.setQuantity(quantity);
                            System.out.println("Quantité modifiée !");
                            break;
                        }catch (NumberFormatException e){
                            System.out.println("Entrez un entier valide.");
                        }
                        catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }break;
                    case "7":   while(true){
                        System.out.println("Entrer le nouveau **chemin local** de l'image ou son nouveau **URL**: ");
                        String pathName = sc.nextLine().trim();
                        try{
                            p.setImagePath(pathName);
                            break;
                        }catch (IllegalArgumentException e){
                            System.out.println(e.getMessage());
                        }
                    }break ;
                    default :  System.out.println("Choix invalide. Essayez encore.");
                        continue;
                }
                while(true){
                    System.out.println("Modifier autre chose ? (yes/no):");
                    String repeat =sc.nextLine().trim().toLowerCase();
                    switch (repeat){
                        case "no" : break mainLoop;
                        case "yes": continue mainLoop ;
                        default: System.out.println("Répondez par 'yes' ou 'no'.");
                    }
                }
            }
            System.out.println("Produit modifié avec succès !");
        }


    }
    public void supprimerProduit(){
        Product.showAllProducts();
        System.out.println("Entrer le titre du produit que vous voulez supprimer :");
        String titleInput = sc.nextLine().trim();
        Product p =Product.findProductByTitle(titleInput);
        if(p ==null){
            System.out.println("Ce produit n'existe pas");
        }
        else{
            System.out.println("Êtes-vous sûr de vouloir supprimer ce produit ? (oui/non)");
            String confirmation =sc.nextLine().trim().toLowerCase();
            if("oui".equals(confirmation)){
                Product.removeFromAllPrducts(p);
                System.out.println("Produit supprimé avec succès.");
            }
            else {
                System.out.println("Suppression annulée.");
            }
        }
    }
    public void addproduct(Product product){
        if(products.contains(product)){
            System.out.println("Ce produit est deja cree");
        }
        else{
            products.add(product);
            product.setPM(this);
        }
    }
    public void removeProduct(Product product){
        products.remove(product);
        product.setPM(null);
    }


    public void showProducts(){
       for(Product product:products){
            System.out.println(product);
       }
    }
}
