package fit_zone.data;

import fit_zone.domain.Client;

import java.util.List;

public interface IClientDAO {
    List<Client> listClients();
    boolean searchClientByID(Client client);
    boolean addClient (Client client);
    boolean editClient(Client client);
    boolean deleteClient(Client client);
}
