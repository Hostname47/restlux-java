package core;

import java.util.*;

public class Commande {
    private int id ;
    private Date date_commande ;
    private static int idHelp=0 ;
    private Client client;
    private Map<Product ,Integer> map = new HashMap<>();

    public Commande(Date date_commande){
        this.date_commande=date_commande;
        idHelp++;
        this.id = idHelp ;
    }
    public Commande(){
        idHelp++;
        this.id = idHelp;
    }
    public void addProduct(Product product , int quantity){
        if(product==null || quantity<=0){
            throw  new IllegalArgumentException("Produit invalide ou quantité non valide.");
        }
        int ProductQuantityInStock = product.getQuantity();
        int QurrentProductQuantityInOrder = map.getOrDefault(product,0);
        int TotalQuantityWanted = QurrentProductQuantityInOrder +quantity;
        if(TotalQuantityWanted>ProductQuantityInStock){
            throw new IllegalArgumentException("Quantité demandée dépasse le stock disponible");
        }
        map.put(product,TotalQuantityWanted);
    }
    public void removeProduct(Product product ,int removeQuantity){
        if(product==null || removeQuantity<=0){
            throw new IllegalArgumentException("Produit invalide ou quantité non valide.");
        }
        if(!map.containsKey(product)){
            System.out.println("Ce produit n'existe pas");
            return;
        }
        int currentProductQuantity =map.get(product);
        if(removeQuantity>=currentProductQuantity){
            map.remove(product);
            System.out.println("Produit supprimé complètement de la commande.");
        }
        else{
            map.put(product,currentProductQuantity -removeQuantity);
            System.out.println("Quantité réduite. Nouvelle quantité: " + (currentProductQuantity - removeQuantity));
        }
    }



    public Client getClient() {
        return client;
    }

    public int getId(){
        return id ;
    }

    public Date getDate_commande(){
        return date_commande ;
    }




    public void setClient(Client client){
        this.client=client;
    }
    public void setDate_commande(Date date_commande) {
        this.date_commande = date_commande;
    }

    public boolean EstAnnuler(){
        return  false ;
    }
    @Override
    public String toString() {
        String result = "";
        result += "Commande ID : " + id + "\n" +
                "Date        : " + date_commande + "\n" +
                "Client      : " + (client != null ? client : "Aucun client") + "\n" +
                "Produits    :\n";

        if (map.isEmpty()) {
            result += "  Aucun produit dans cette commande.\n";
        } else {
            for (Map.Entry<Product, Integer> entry : map.entrySet()) {
                Product p = entry.getKey();
                int qte = entry.getValue();
                result += "  - " + p.getTitle() + " | Quantité: " + qte + "\n";
            }
        }
        return result;
    }
}
