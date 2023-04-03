import Model.Client;
import Model.User;
import Presenter.ViewClientsPresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ViewClientsPresenterTest {

    private ViewClientsPresenter presenter;

    @BeforeEach
    public void setUp() {
        presenter = new ViewClientsPresenter();
    }

    @Test
    public void testAllClients() {
        List<Client> expectedClients = new ArrayList<>();
        expectedClients.add(new Client(new User("NewName", "555-9876", "newemail@gmail.com", "client", "newusername", "newpassword")));

        List<Client> actualClients = presenter.allClients();

        for (int i = 0; i < expectedClients.size(); i++) {
            assertEquals(expectedClients.get(i).getUser().getName(), actualClients.get(i).getUser().getName());
            assertEquals(expectedClients.get(i).getUser().getPhone(), actualClients.get(i).getUser().getPhone());
            assertEquals(expectedClients.get(i).getUser().getEmail(), actualClients.get(i).getUser().getEmail());
            assertEquals(expectedClients.get(i).getUser().getUsername(), actualClients.get(i).getUser().getUsername());
            assertEquals(expectedClients.get(i).getUser().getPassword(), actualClients.get(i).getUser().getPassword());
        }
    }

    @Test
    public void testCreateClient() {
        User newUser = new User("Lica", "555-4321", "dkg@gmail.com", "client", "kaedgrhb", "password");
        Client newClient = new Client(newUser);
        presenter.createClient(newClient);

        List<Client> actualClients = presenter.allClients();
        Client expectedClient = presenter.findClient(newClient.getIdClient());

        assertTrue(actualClients.contains(expectedClient));
    }

    @Test
    public void testDeleteClient() {
        List<Client> clientsBeforeDeletion = presenter.allClients();

        presenter.deleteClient(clientsBeforeDeletion.get(0).getIdClient());

        List<Client> clientsAfterDeletion = presenter.allClients();

        assertEquals(clientsBeforeDeletion.size() - 1, clientsAfterDeletion.size());
    }

    @Test
    public void testModifyClient() {
        List<Client> clientsBeforeModification = presenter.allClients();

        Client clientToModify = clientsBeforeModification.get(0);
        Long id = clientToModify.getIdClient();
        String newName = "NewName";
        String newPhone = "555-9876";
        String newEmail = "newemail@gmail.com";
        String newUsername = "newusername";
        String newPassword = "newpassword";
        presenter.modifyClient(id, newName, newPhone, newEmail, newUsername, newPassword);

        Client modifiedClient = presenter.findClient(id);

        assertEquals(newName, modifiedClient.getUser().getName());
        assertEquals(newPhone, modifiedClient.getUser().getPhone());
        assertEquals(newEmail, modifiedClient.getUser().getEmail());
        assertEquals(newUsername, modifiedClient.getUser().getUsername());
        assertEquals(newPassword, modifiedClient.getUser().getPassword());
    }

    @Test
    public void testFindClient() {
        List<Client> clients = presenter.allClients();
        Client expectedClient = clients.get(0);

        Client actualClient = presenter.findClient(expectedClient.getIdClient());

        assertEquals(expectedClient.getUser().getName(), actualClient.getUser().getName());
        assertEquals(expectedClient.getUser().getPhone(), actualClient.getUser().getPhone());
        assertEquals(expectedClient.getUser().getEmail(), actualClient.getUser().getEmail());
        assertEquals(actualClient.getUser().getPassword(), expectedClient.getUser().getPassword());
    }
}