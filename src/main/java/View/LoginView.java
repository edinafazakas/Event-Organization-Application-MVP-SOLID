package View;
import Presenter.LoginPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LoginView extends JFrame implements ActionListener, LoginViewInterface{
    private JPanel Login;
    private JTextField username;
    private JButton LOGINButton;
    private JButton REGISTERButton;
    private JPasswordField parola;
    private JButton EXITButton;
    private LoginPresenter loginPresenter;

    public LoginView(){
        setTitle("Login");
        setContentPane(Login);
        setSize(new Dimension(500, 480));
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        loginPresenter = new LoginPresenter();

        this.LOGINButton.addActionListener(this);
        this.REGISTERButton.addActionListener(this);
        this.EXITButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == LOGINButton){
            login();
        }

        if(e.getSource()==REGISTERButton){
            this.dispose();
            new RegisterView();

        }
        if(e.getSource()==EXITButton){
            this.dispose();
        }
    }

    @Override
    public void login() {
        this.dispose();
        String string = loginPresenter.checkLogin(username.getText(), parola.getText());
        if(string != null){
            JOptionPane.showMessageDialog(null, "Autentificare cu succes!");
            if (string.equals("admin")){
                this.dispose();
                new AdminView();

            } else if (string.equals("client")) {
                this.dispose();
                new EventsView();
            }
            else if(string.equals("eventCoord")){
                this.dispose();
                new EventCoordView();
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Username sau parola gresita.");
        }
        this.dispose();
    }
}