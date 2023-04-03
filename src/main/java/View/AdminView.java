package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminView extends JFrame implements ActionListener, AdminViewInterface {
    private JButton VIEWEVENTSButton;
    private JButton VIEWEVENTSCOORDONATORSButton;
    private JButton BACKButton;
    private JPanel p;
    private JButton VIEWCLIENTSButton;

    public AdminView(){
        this.setTitle("Event Coord");
        this.setContentPane(p);
        this.setSize(new Dimension(1010, 700));
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        this.VIEWEVENTSCOORDONATORSButton.addActionListener(this);
        this.VIEWEVENTSButton.addActionListener(this);
        this.BACKButton.addActionListener(this);
        this.VIEWCLIENTSButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == VIEWEVENTSCOORDONATORSButton){
            this.dispose();
            new ViewEventCoordView();
        }

        if(e.getSource() == VIEWEVENTSButton){
            this.dispose();
            new EventsView();
        }

        if(e.getSource() == BACKButton){
            this.dispose();
            new MainPageView();
        }

        if(e.getSource() == VIEWCLIENTSButton){
            this.dispose();
            new ClientsPersAdminView();
        }
    }
}
