package core;

import security.Permission;

import java.sql.Timestamp;
import java.util.Scanner;

public class Admin extends Personnel {

    public  Admin( String name, String UsernameEmail, String password, Timestamp last_activity){
        super(name,UsernameEmail,password,last_activity);
    }
    public Admin(){

    }
    Scanner sc = new Scanner(System.in);
    // this one is for the admin , so he can create roles and put them in array list in the class Role
//    public void createNewPermission(){
//        Permission p = new Permission("", "");
//        System.out.println("Ajouter une permission");
//        while(true){
//            System.out.println("Entrer le nom de la permission");
//            String input = sc.nextLine().trim();
//            try {
//                p.setName(input);
//                break;
//            }catch (IllegalArgumentException e){
//                System.out.println(e.getMessage());
//            }
//        }
//        while(true){
//            System.out.println("Entrer une description");
//            String input = sc.nextLine().trim();
//            try{
//                p.setDescription(input);
//                break;
//            }catch (IllegalArgumentException e){
//                System.out.println(e.getMessage());
//            }
//        }
//        Permission.addToAllPermissions(p);
//        System.out.println("Permission ajoute");
//    }
//    public void removeFromAllPermissions(String permissionName){
//            Permission.removeFromAllPermissions(permissionName);
//    }
    public void createNewRole (){
        Role r =new Role();
        System.out.println("Ajouter un role");
        while(true){
            System.out.println("Entrer le nom du role");
            String input = sc.nextLine().trim();
            try{
                r.setName(input);
                break;
            }catch (IllegalArgumentException e ){
                System.out.println(e.getMessage());
            }
        }
        while(true){
            System.out.println("Entrer une description");
            String input = sc.nextLine().trim();
            try{
                r.setDescription(input);
                break;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
        Role.addToAllRoles(r);
        System.out.println("Rôle '" + r.getName() + "' a été créé avec succès !");
    }

    public void removeRole(String rolename){
        Role.removeFromAllRoles(rolename);
    }

    public void showAllRoles(){
        Role.showAllRoles();
    }

//    public void AjouterCompteEmployer(){
//        System.out.println("* Ajouter compte pour employes. *");
//        mainLoop:
//        while(true){
//            System.out.println("Choisir le type d'employe \n");
//            System.out.println("1)Client Manager");
//            System.out.println("2)Product Manager");
//            System.out.println("3)Director");
//            System.out.println("4)Client");
//            System.out.println("5)Worker");
//            String input = sc.nextLine().trim();
//            switch (input){
//                case "1":   ClientManager cm= new ClientManager();
//                            cm.SignUp();
//                            Role clientManagerRole = Role.findRoleByName("client manager");
//                            cm.addRole(clientManagerRole);
//                            System.out.println("Compte pour client manager est crée avec succès !");
//                            break;
//
//                case "2":  ProductManager pm = new ProductManager();
//                           pm.SignUp();
//                           Role productManagerRole = Role.findRoleByName("product manager");
//                           pm.addRole(productManagerRole);
//                           System.out.println("Compte pour product manager est crée avec succès !");
//                           break;
//
//                case "3": Director d = new Director();
//                          d.SignUp();
//                          Role directorRole =Role.findRoleByName("admin");
//                          d.addRole(directorRole);
//                          System.out.println("Compte pour directeur est crée avec succès !");
//                          break;
//
//                case "4": Client c = new Client();
//                          c.SignUp();
//                          Role clientRole =Role.findRoleByName("client");
//                          c.addRole(clientRole);
//                          System.out.println("Compte pour client est crée avec succès !");
//                          break;
//
//                case "5": Personnel p = new Personnel();
//                          p.SignUp();
//                          Role personnelRole = Role.findRoleByName("worker");
//                          p.addRole(personnelRole);
//                          System.out.println("Compte pour personnel est crée avec succès !");
//                          break;
//
//                default: System.out.println("Choix invalide. Essayez encore.");
//                         continue mainLoop;
//                        }
//           while(true){
//               System.out.println("Voulez-vous ajouter un autre compte ? (oui/non)");
//               String repeat = sc.nextLine().toLowerCase().trim();
//               switch (repeat){
//                   case "yes": continue mainLoop;
//                   case "no" : break mainLoop;
//                   default: continue ;
//               }
//           }
//        }
//    }


//    public void ModifierCompteEmployer(){
//        mainLoop:
//        while(true){
//            User.showAllUsers();
//            System.out.println("Entrer Id du compte que vous voulez modifier ");
//            int idInput = sc.nextInt();
//            sc.nextLine();
//            User u = User.findFromAllUsersById(idInput);
//            if(u==null){
//                System.out.println("User n'existe pas ");
//                continue mainLoop;
//            }
//            else {
//                u.ChangeInfo();
//                while(true){
//                    System.out.println("Entrer le nouveau role pour cet employe");
//                    System.out.println("Choisir le type d'employe \n");
//                    System.out.println("1)Client Manager");
//                    System.out.println("2)Product Manager");
//                    System.out.println("3)Director");
//                    System.out.println("4)Client");
//                    System.out.println("5)Admin");
//                    System.out.println("6)Worker");
//                    String input = sc.nextLine().trim();
//                    Role newRole =null;
//                    switch (input){
//                        case "1": newRole = Role.findRoleByName("client manager");break;
//                        case "2": newRole = Role.findRoleByName("product manager");break;
//                        case "3": newRole = Role.findRoleByName("director");break;
//                        case "4": newRole = Role.findRoleByName("client");break;
//                        case "5": newRole = Role.findRoleByName("admin");break;
//                        case "6": newRole = Role.findRoleByName("worker");break;
//                        default: System.out.println("Choix invalide. Essayez encore.");
//                                 continue ;
//                    }
//                    if(newRole==null){
//                        System.out.println("Le rôle n'existe pas. Veuillez d'abord le créer.");
//                        continue ;
//                    }
//                        u.addRole(newRole);
//                        break ;
//                    }
//                while(true){
//                    System.out.println("Voulez-vous modifier un autre compte ? (oui/non)");
//                    String repeat = sc.nextLine().toLowerCase().trim();
//                    switch (repeat){
//                        case "yes": continue mainLoop;
//                        case "no" : break mainLoop;
//                        default: continue ;
//                    }
//                }
//                }
//        }
//    }

//    public void SupprimerCompteEmployer(){
//        while(true){
//            User.showAllUsers();
//            System.out.println("Entrer Id du compte que vous voulez supprimer ");
//            int idInput = sc.nextInt();
//            sc.nextLine();
//            User u = User.findFromAllUsersById(idInput);
//            if(u==null){
//                System.out.println("User n'existe pas ");
//                continue;
//            }
//            else{
//                User.removeFromAllusers(u);
//                System.out.println("Utilisateur supprime avec succès !");
//                break;
//            }
//        }
//    }

}
