package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPageView extends JFrame implements ActionListener, MainPageViewInterface {
    private JPanel p;
    private JButton EVENTSButton;
    private JButton LOGINButton;
    private JButton REGISTERButton;
    private JButton EXITButton;

    public MainPageView() {

        setTitle("Main Page");
        setContentPane(p);
        setSize(new Dimension(500, 480));
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.EVENTSButton.addActionListener(this);
        this.LOGINButton.addActionListener(this);
        this.REGISTERButton.addActionListener(this);
        this.EXITButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==LOGINButton){
            this.dispose();
            new LoginView();
        }

        if(e.getSource()==REGISTERButton){
            this.dispose();
            new RegisterView();
        }

        if(e.getSource()==EXITButton){
            this.dispose();
        }

        if(e.getSource()==EVENTSButton){
            this.dispose();
            new EventsView();
        }
    }


}
