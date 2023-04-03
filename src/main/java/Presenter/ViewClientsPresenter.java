package Presenter;

import Model.Client;
import Model.Events;
import Model.Persistence.ClientPersistence;
import Model.Persistence.UserPersistence;
import Model.User;

import java.util.List;

public class ViewClientsPresenter implements ViewClientsPresenterInterface{

    private ClientPersistence clientPersistence;
    private UserPersistence userPersistence;

    public ViewClientsPresenter(){
        this.clientPersistence = new ClientPersistence();
        this.userPersistence = new UserPersistence();
    }
    @Override
    public List<Client> allClients() {
        List<Client> clients = clientPersistence.getAllClients();
        return clients;
    }

    @Override
    public void createClient(Client client) {
        clientPersistence.createClient(client);
    }

    @Override
    public void deleteClient(Long id) {
        clientPersistence.deleteClient(id);
    }

    @Override
    public void modifyClient(Long id, String name, String phone, String mail, String username, String password) {
        System.out.println("\n" + id);
        Client client  = clientPersistence.getClient(id);
        client.getUser().setName(name);
        client.getUser().setPhone(phone);
        client.getUser().setEmail(mail);
        client.getUser().setUsername(username);
        client.getUser().setPassword(password);
        clientPersistence.updateClient(client);
    }

    @Override
    public Client findClient(Long id) {
        Client client = clientPersistence.getClient(id);
        return client;
    }

    @Override
    public List<Client> searchClient() {
        return null;
    }
}
