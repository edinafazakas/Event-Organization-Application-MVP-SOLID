package Presenter;

import Model.Persistence.UserPersistence;
import Model.User;

public class LoginPresenter implements LoginPresenterInterface{

    private UserPersistence userPersistence;

    public LoginPresenter(){
        userPersistence = new UserPersistence();
    }
    @Override
    public String checkLogin(String s1, String s2) {
        User user = userPersistence.findUserByUsername(s1);
        if(user != null){
            String username = user.getUsername();
            if(username.equals(s1) && user.getPassword().equals(s2)){
                return user.getTypeUser();
            }
        }
        return null;
    }
}
