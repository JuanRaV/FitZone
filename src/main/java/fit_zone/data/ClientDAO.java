package fit_zone.data;

import fit_zone.domain.Client;

import java.util.List;

public class ClientDAO implements IClientDAO{
    @Override
    public List<Client> listClients() {
        return List.of();
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
}
