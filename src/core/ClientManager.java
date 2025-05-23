package core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ClientManager extends Personnel implements Ifonctions {

    private List<Commande> commandes = new ArrayList<>();
    public void addCommande(Commande commande){
        if(!commandes.contains(commande)){
            commandes.add(commande);
        }
    }
    public void removeCommande(Commande commande){
        commandes.remove(commande);
    }

    public void consulterCommande(){
        for(Commande commande:commandes){
            System.out.println(commande);
        }
    }


    public void creerCommande(){
        Scanner sc = new Scanner(System.in);
        Client client = new Client();
        Commande cmd = new Commande(new Date());
        mainLoop:
        while(true) {
            Product.showAllProducts();
            System.out.print("Entrez le titre du produit que vous souhaitez ajouter : ");
            String inputP = sc.nextLine().trim();
            Product product = Product.findProductByTitle(inputP);
            if (product == null) {
                System.out.println("Produit introuvable.");
                continue mainLoop;
            }
            System.out.print("Quantité souhaitée pour " + product.getTitle() + " : ");
            String inputQuantity = sc.nextLine().trim();
            try {
                int quantity = Integer.parseInt(inputQuantity);
                cmd.addProduct(product, quantity);
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            while(true){
                System.out.println("Voulez vous ajouter un autre produit ? yes/no");
                String repeat = sc.nextLine().trim();
                switch (repeat){
                    case"yes": continue mainLoop;
                    case"no": break mainLoop;
                    default: System.out.println("Choix invalide");continue ;
                }
            }
        }
        client.addCommande(cmd);
        addCommande(cmd);
        System.out.println("Commande créée avec succès !");
    }

    public void consulterListCommande(){

    }

    public void consulterListReservation(){

    }
    public void ajouterAbonnementSite(){

    }
    public void modiferAbonnementSite(){

    }
    public void supprimerAbonnementSite(){

    }
    public void validerCommande(){

    }
    public void annulerCommande(){

    }
    public void validerReservation(){

    }
    public void annulerReservation(){

    }


}
