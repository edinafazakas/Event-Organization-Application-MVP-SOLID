package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventCoordView extends JFrame implements ActionListener, EventCoordViewInterface {
    private JPanel p;
    private JButton VIEWEVENTSButton;
    private JButton VIEWCLIENTSButton;
    private JButton BACKButton;

    public EventCoordView(){
        this.setTitle("Event Coord");
        this.setContentPane(p);
        this.setSize(new Dimension(1010, 700));
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        this.VIEWCLIENTSButton.addActionListener(this);
        this.VIEWEVENTSButton.addActionListener(this);
        this.BACKButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == VIEWCLIENTSButton){
            this.dispose();
            new ViewClientsView();
        }

        if(e.getSource() == VIEWEVENTSButton){
            this.dispose();
            new AdminEventsPersView();
        }

        if(e.getSource() == BACKButton){
            this.dispose();
            new MainPageView();
        }

    }
}
