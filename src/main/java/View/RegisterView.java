package View;
import Model.User;
import Presenter.RegisterPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterView extends JFrame implements ActionListener, RegisterViewInterface{
    private JPanel register;
    private JTextField nume;
    private JTextField telefon;
    private JTextField mail;
    private JTextField username;
    private JButton REGISTERButton;
    private JButton BACKButton;
    private JButton EXITButton;
    private JPasswordField parola;
    private RegisterPresenter registerPresenter;

    public void setUsername(JTextField username) {
        this.username = username;
    }

    public RegisterView(){
        setTitle("Register");
        setContentPane(register);
        setSize(new Dimension(500, 480));
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        registerPresenter = new RegisterPresenter();

        this.REGISTERButton.addActionListener(this);
        this.BACKButton.addActionListener(this);
        this.EXITButton.addActionListener(this);

    }

    public void registerListener(ActionListener actionListener){
        this.REGISTERButton.addActionListener(actionListener);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==BACKButton){
            this.dispose();
            new LoginView();
        }
        if(e.getSource()==EXITButton){
            this.dispose();
        }
        if(e.getSource()==REGISTERButton){
            registerPresenter.createClient(new User(nume.getText(), telefon.getText(), mail.getText(), "client", username.getText(), parola.getText()));
            this.dispose();
            new MainPageView();
        }
    }


    public void backListener(ActionListener actionListener){
        this.BACKButton.addActionListener(actionListener);
    }

    public void exitListener(ActionListener actionListener){
        this.EXITButton.addActionListener(actionListener);
    }

    public JPanel getRegister() {
        return register;
    }

    public String getNume() {
        return nume.getText();
    }


    public String getTelefon() {
        return telefon.getText();
    }

    public String getMail() {
        return mail.getText();
    }

    public JButton getREGISTERButton() {
        return REGISTERButton;
    }

    public JButton getBACKButton() {
        return BACKButton;
    }

    public JButton getEXITButton() {
        return EXITButton;
    }

    public String getParola() {
        return parola.getText();
    }

    public String getUsername() {
        return username.getText();
    }
}

