package View;
import Model.Events;
import Presenter.EventsPresenter;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EventsView extends JFrame implements ActionListener, EventsViewInteface {
    private JPanel p;
    private JTable table;
    private JComboBox comboBox1;
    private JButton SORTButton;
    private JButton VIEWButton;
    private JButton BACKButton;
    private List<Events> events;
    private EventsPresenter eventsPresenter;
    EventsTableModel eventsTableModel;

    public EventsView(){
        this.setTitle("View Events");
        this.setContentPane(p);
        this.setSize(new Dimension(1010, 700));
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.eventsPresenter = new EventsPresenter();
        events = new ArrayList<>();

        this.VIEWButton.addActionListener(this);
        this.SORTButton.addActionListener(this);
        this.BACKButton.addActionListener(this);

        eventsTableModel = new EventsTableModel(events);
        table.setModel(eventsTableModel);
        table.setAutoCreateRowSorter(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == VIEWButton){
            events = getAllEvents();
            setTableContents(events);
        }

        if(e.getSource() == SORTButton){
            sortEvents();
        }

        if(e.getSource() == BACKButton){
            this.dispose();
            new MainPageView();
        }

    }

    @Override
    public List<Events> getAllEvents() {
        return eventsPresenter.allEvents();
    }

    @Override
    public void sortEvents() {
        List<Events> events1 = new ArrayList<>();
        if(comboBox1.getSelectedItem().equals("scop")){
            events1 = eventsPresenter.sortEvents("scop");
        }
        else if(comboBox1.getSelectedItem().equals("locatie")){
            events1 = eventsPresenter.sortEvents("locatie");
        }
        setTableContents(events1);
    }

    @Override
    public void setTableContents(List<Events> events1) {
        eventsTableModel = new EventsTableModel(events1);
        table.setModel(eventsTableModel);
        table.setAutoCreateRowSorter(true);
    }

    public static class EventsTableModel extends AbstractTableModel {

        private final String[] columns = {"ID", "Scop", "Locatie", "Data", "Numar persoane"};
        private List<Events> eventsList;

        public EventsTableModel(List<Events> eventsList) {
            this.eventsList = eventsList;
        }

        @Override
        public int getRowCount() {
            return eventsList.size();
        }

        @Override
        public int getColumnCount() {
            return columns.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex) {
                case 0 -> eventsList.get(rowIndex).getIdEvent();
                case 1 -> eventsList.get(rowIndex).getScop();
                case 2 -> eventsList.get(rowIndex).getLocatie();
                case 3 -> eventsList.get(rowIndex).getData();
                case 4 -> eventsList.get(rowIndex).getNumarPersoane();

                default -> "-";
            };
        }
        @Override
        public String getColumnName(int column){
            return columns[column];
        }
    }
}
