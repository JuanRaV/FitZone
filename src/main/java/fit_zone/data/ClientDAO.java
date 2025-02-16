package fit_zone.data;

import fit_zone.connection.ConnectionDB;
import fit_zone.domain.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static fit_zone.connection.ConnectionDB.getConnection;

public class ClientDAO implements IClientDAO{
    @Override
    public List<Client> listClients() {
        List<Client> clients = new ArrayList<>();
        //This Interface will allow us to prepare SQL Statement
        PreparedStatement ps;
        //This Interface will receive the information from the consult
        ResultSet rs;
        Connection conn = getConnection();
        var sql = "SELECT * FROM client ORDER BY id";

        try{
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                var client = new Client();
                client.setId(rs.getInt("id"));
                client.setFirstName(rs.getString("firstName"));
                client.setLastName(rs.getString("lastName"));
                client.setMembership(rs.getInt("membership"));
                clients.add(client);
            }
        } catch (Exception e) {
            System.out.println("ERROR DURING GETTING CLIENTS: " + e.getMessage());
        } finally {
            try{
                conn.close();
            }catch(Exception e){
                System.out.println("ERROR DURING CLOSING CONNECTION: " + e.getMessage());
            }
        }

        return clients;
    }

    @Override
    public boolean searchClientByID(Client client) {
        PreparedStatement ps;
        ResultSet rs;
        Connection conn = getConnection();
        String sql = "SELECT * FROM client WHERE id = ?";
        try{
            ps = conn.prepareStatement(sql);
            //Send a parameter
            //We will send only 1 parameter, and client id
            ps.setInt(1,client.getId());
            rs = ps.executeQuery();
            //Check if we have a record to read
            if(rs.next()){
                client.setFirstName(rs.getString("firstName"));
                client.setLastName(rs.getString("lastName"));
                client.setMembership(rs.getInt("membership"));
                return true;
            }
        } catch (Exception e) {
            System.out.println("ERROR DURING GETTING CLIENT WITH ID: " + e.getMessage());
        } finally {
            try{
                conn.close();
            } catch (Exception e) {
                System.out.println("ERROR CLOSING DB CONNECTION: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean addClient(Client client) {
        PreparedStatement ps;
        Connection conn = getConnection();
        String sql = "INSERT INTO client (firstName, lastName, membership) VALUES (?, ?, ?)";
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1, client.getFirstName());
            ps.setString(2, client.getLastName());
            ps.setInt(3, client.getMembership());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("ERROR DURING INSERTING CLIENT: " + e.getMessage() );
        }finally {
            try{
              conn.close();
            } catch (Exception e) {
                System.out.println("ERROR CLOSING CONNECTION: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean editClient(Client client) {
        PreparedStatement ps;
        Connection conn = getConnection();
        String sql = "UPDATE client SET firstName=?, lastName=?, membership=? WHERE id = ?";
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1,client.getFirstName());
            ps.setString(2, client.getLastName());
            ps.setInt(3,client.getMembership());
            ps.setInt(4, client.getId());
            ps.execute();
            return true;
        }catch(Exception e){
            System.out.println("ERROR DURING UPDATING USER: " + e.getMessage());
        }finally {
            try{
                conn.close();
            } catch (Exception e) {
                System.out.println("ERROR DURING CLOSING DB CONNECTION: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean deleteClient(Client client) {
        PreparedStatement ps;
        Connection conn = getConnection();
        String sql = "DELETE FROM client WHERE id = ?";
        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1, client.getId());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("ERROR DURING DELETING USER: " + e.getMessage());
        }finally {
            try{
                conn.close();
            } catch (Exception e) {
                System.out.println("ERROR CLOSING DB CONNECTION: "+  e.getMessage());
            }
        }
        return false;
    }

    public static void main(String[] args) {
        IClientDAO clientDao = new ClientDAO();


        //Search by ID
//        var client = new Client(5);
//        System.out.println("CLIENT BEFORE THE SEARCH: " + client);
//        var found = clientDao.searchClientByID(client);
//        if(found)
//            System.out.println("CLIENT FOUND: " + client);
//        else
//            System.out.println("CLIENT NOT FOUND WITH ID: " + client.getId());

        //Add Client
//        Client client = new Client("Sonia", "Lopez", 600);
//        var clientCreatedSuccessfully = clientDao.addClient(client);
//        if(clientCreatedSuccessfully)
//            System.out.println("CLIENT CREATED SUCCESSFULLY");
//        else
//            System.out.println("CLIENT CANNOT BE CREATED");

        //Edit client
//        Client client = new Client(1, "Juan Ramon", "Virgen", 100);
//        boolean modified = clientDao.editClient(client);
//        if(modified)
//            System.out.println("CLIENT EDITED SUCCESSFULLY");
//        else
//            System.out.println("SOMETHING WNT WRONG");

        //Delete client
        Client client = new Client(8);
        boolean deleted = clientDao.deleteClient(client);
        if(deleted)
            System.out.println("CLIENT DELETED SUCCESSFULLY");
        else
            System.out.println("SOMETHING WENT WRONG");
        //List clients test
        System.out.println(" *** CLIENTS LIST ***");
        List<Client> clients = clientDao.listClients();
        clients.forEach(System.out::println);
    }
}
