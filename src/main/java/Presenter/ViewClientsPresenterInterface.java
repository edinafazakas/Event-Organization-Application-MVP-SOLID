package Presenter;

import Model.Client;
import Model.User;

import java.util.List;

public interface ViewClientsPresenterInterface {
    public List<Client> allClients();
    public void createClient(Client client);
    public void deleteClient(Long id);
    public void modifyClient(Long id, String name, String phone, String mail, String username, String password);
    public Client findClient(Long id);
    public List<Client> searchClient();


}
