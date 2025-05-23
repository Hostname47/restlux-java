package core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Client extends User implements Ifonctions{
    private List<Commande> commandes = new ArrayList<>();

    public void consulterReservation(){

    }

    public void creerCommande(){
        Scanner sc = new Scanner(System.in);
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
            this.addCommande(cmd);
            System.out.println("Commande créée avec succès !");
    }

    public void consulterCommande(){
        for(Commande commande:commandes){
            System.out.println(commande);
        }
    }
    public void addCommande(Commande commande){
        if(!commandes.contains(commande)){
            commandes.add(commande);
            commande.setClient(this);
        }
    }
    public void removeCommande(Commande commande){
        commandes.remove(commande);
    }

    public void ajouterCompte(){
        signup();
        Role clientRole=Role.findRoleByName("client");
        // this.addRole(clientRole);
        System.out.println("Votre compte a été créé avec succès !");


    }
    public void modifierCompte(){
        // this.ChangeInfo();
        System.out.println("Votre compte a été modifié !");

    }
    public void supprimerCompte(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Confirmer suppression de compte");
        String answer = sc.nextLine().trim();
        if(answer.equalsIgnoreCase("oui")){
            // User.removeFromAllusers(this);
            System.out.println("Votre compte a été supprimé !");
        }
        else {
            System.out.println("Suppression annulée");
        }
    }
}
