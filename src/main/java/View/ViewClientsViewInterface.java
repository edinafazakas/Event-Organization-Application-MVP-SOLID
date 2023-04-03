package View;

import Model.Client;
import Model.Events;

import java.util.List;

public interface ViewClientsViewInterface {

    public void tableSelection();
    public void getTableContents(List<Client> clients);
    public void findClient(Client client);
}
