package fit_zone.presentation;

import fit_zone.data.ClientDAO;
import fit_zone.data.IClientDAO;
import fit_zone.domain.Client;

import java.util.Scanner;
public class FitZoneApp {
    public static void main(String[] args) {
        fitZoneApp();
    }

    private static void fitZoneApp(){
        var exit = false;
        var console = new Scanner(System.in);
        //Create an Object of ClientDAO
        IClientDAO clientDao = new ClientDAO();
        while(!exit){
            try{
                var option = showMenu(console);
                exit = executeOptions(console, option, clientDao);
            }catch(Exception e){
                System.out.println("ERROR EXECUTING OPTIONS: " + e.getMessage());
            }finally {
                System.out.println();
            }
        }
    }

    private static int showMenu(Scanner console){
        System.out.print("""
                *** FIT ZONE GYM ***
                1.- Get Clients
                2.- Search Client
                3.- Add Client
                4.- Edit Client
                5.- Delete Client
                6.- Exit
                Choose an option: \s""");
        return Integer.parseInt(console.nextLine());
    }

    private static boolean executeOptions(Scanner console, int option, IClientDAO clientDao){
        var exit = false;
        switch (option){
            case 1 -> {
                System.out.println("--- CLIENTS LIST ---- ");
                var clients = clientDao.listClients();
                clients.forEach(System.out::println);
            }
            case 2->{
                System.out.println("--- SEARCH A CLIENT BY ID ---");
                System.out.print("Write Client ID: ");
                var id = Integer.parseInt(console.nextLine());
                var client = new Client(id);
                var found = clientDao.searchClientByID(client);
                if (found){
                    System.out.println("Client with ID " + id + " Found" + client);
                }else{
                    System.out.println("Client not found " + client);
                }
            }
            case 3->{
                System.out.println("--- ADD CLIENT ---");
                System.out.print("Write First Name: ");
                var firstName = console.nextLine();
                System.out.print("Write Last Name: ");
                var lastName = console.nextLine();
                System.out.print("Write Membership: ");
                var membership = Integer.parseInt(console.nextLine());

                var client = new Client(firstName, lastName, membership);
                var addedSuccessfully = clientDao.addClient(client);
                if(addedSuccessfully){
                    System.out.println("Client Added Successfully: " + client);
                }else{
                    System.out.println("There Was An Error Adding Client: "  + client);
                }
            }
            case 4->{
                System.out.println("--- EDIT CLIENT --- ");
                System.out.print("Write Client ID: ");
                int ID = Integer.parseInt(console.nextLine());
                System.out.print("Write First Name: ");
                var firstName = console.nextLine();
                System.out.print("Write Last Name: ");
                var lastName = console.nextLine();
                System.out.print("Write Membership: ");
                var membership = Integer.parseInt(console.nextLine());
                var client = new Client(ID, firstName, lastName, membership);
                var modified = clientDao.editClient(client);
                if(modified){
                    System.out.println("Client Modified Successfully: " + client);
                }else{
                    System.out.println("Client NOT Modified: " + client);
                }
            }
            case 5->{
                System.out.println("--- DELETE CLIENT --- ");
                System.out.print("Write Client ID: ");
                int ID = Integer.parseInt(console.nextLine());
                var client = new Client(ID);
                var deleted = clientDao.deleteClient(client);
                if(deleted){
                    System.out.println("Client Deleted Successfully: " + client);
                }else{
                    System.out.println("Client NOT Deleted: " + client);
                }
            }
            case 6->{
                System.out.println("THANKS FOR USING FIT ZONE APP");
                exit = true;
            }
            default -> System.out.println("Option not valid");

        }

        return exit;
    }
}
