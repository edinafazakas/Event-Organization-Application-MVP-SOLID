package Presenter;

import Model.Client;
import Model.Persistence.ClientPersistence;
import Model.Persistence.UserPersistence;
import Model.User;

public class RegisterPresenter implements RegisterPresenterInterface{

    private ClientPersistence clientPersistence;
    private UserPersistence userPersistence;

    public RegisterPresenter(){
        clientPersistence = new ClientPersistence();
        userPersistence = new UserPersistence();
    }
    @Override
    public void createClient(User user) {
        Client client = new Client(user);
        clientPersistence.createClient(client);
    }
}
