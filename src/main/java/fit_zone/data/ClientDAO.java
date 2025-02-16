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
        return false;
    }

    @Override
    public boolean addClient(Client client) {
        return false;
    }

    @Override
    public boolean editClient(Client client) {
        return false;
    }

    @Override
    public boolean deleteClient(Client client) {
        return false;
    }

    public static void main(String[] args) {
        //List clients test
        System.out.println(" *** CLIENTS LIST ***");
        IClientDAO clientDao = new ClientDAO();
        var clients = clientDao.listClients();
        clients.forEach(System.out::println);
    }
}
