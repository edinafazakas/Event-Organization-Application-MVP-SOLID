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

public class AdminEventsPersView extends JFrame implements ActionListener, EventsPersViewInterface {
    private JTable table;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField3;
    private JButton VIEWEVENTSButton;
    private JButton ADDEVENTButton;
    private JButton MODIFYEVENTButton;
    private JButton DELETEEVENTButton;
    private JButton FINDEVENTButton;
    private JPanel p;
    private JComboBox comboBox1;
    private JTextField textField6;
    private JButton FILTEREVENTSButton;
    private JButton BACKButton;
    private JComboBox comboBox2;
    private JButton SORTEVENTSButton;
    private EventsPresenter eventsPresenter;
    private List<Events> events;
    private int selectedIndex;
    private Events selectedEvent;
    private EventsPersTableModel eventsPersTableModel;


    public AdminEventsPersView() {
        this.setTitle("View Events");
        this.setContentPane(p);
        this.setSize(new Dimension(1100, 800));
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.eventsPresenter = new EventsPresenter();
        events = new ArrayList<>();

        this.VIEWEVENTSButton.addActionListener(this);
        this.DELETEEVENTButton.addActionListener(this);
        this.MODIFYEVENTButton.addActionListener(this);
        this.FINDEVENTButton.addActionListener(this);
        this.ADDEVENTButton.addActionListener(this);
        this.BACKButton.addActionListener(this);
        this.FILTEREVENTSButton.addActionListener(this);
        this.SORTEVENTSButton.addActionListener(this);

        table.getSelectionModel().addListSelectionListener(e -> {
            if (! table.getSelectionModel().isSelectionEmpty()){
                selectedIndex = table.convertRowIndexToModel(table.getSelectedRow());
                selectedEvent = events.get(selectedIndex);
                if(selectedEvent != null){
                    textField1.setText(selectedEvent.getIdEvent().toString());
                    textField2.setText(selectedEvent.getScop());
                    textField3.setText(selectedEvent.getLocatie());
                    textField4.setText(selectedEvent.getData());
                    textField5.setText(selectedEvent.getNumarPersoane());
                }
            }
        });

        eventsPersTableModel = new EventsPersTableModel(events);
        table.setModel(eventsPersTableModel);
        table.setAutoCreateRowSorter(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == VIEWEVENTSButton){
            events = eventsPresenter.allEvents();
            eventsPersTableModel = new EventsPersTableModel(events);
            table.setModel(eventsPersTableModel);
            table.setAutoCreateRowSorter(true);
        }

        if(e.getSource() == ADDEVENTButton){
            eventsPresenter.createEvent(new Events(textField2.getText(), textField3.getText(), textField4.getText(), textField5.getText()));
        }

        if(e.getSource() == MODIFYEVENTButton){
            eventsPresenter.modifyEvent(Long.valueOf(textField1.getText()), textField2.getText(), textField3.getText(), textField4.getText(), textField5.getText());
        }

        if(e.getSource() == DELETEEVENTButton){
            eventsPresenter.deleteEvent(Long.valueOf(textField1.getText()));
        }

        if(e.getSource() == FINDEVENTButton){
            Events events1 = eventsPresenter.findEvent(Long.valueOf(textField1.getText()));
            textField1.setText(String.valueOf(events1.getIdEvent()));
            textField2.setText(events1.getScop());
            textField3.setText(events1.getLocatie());
            textField4.setText(events1.getData());
            textField5.setText(events1.getNumarPersoane());
        }

        if(e.getSource() == FILTEREVENTSButton){
            System.out.println(comboBox1.getSelectedItem());
            List<Events> eventsList = eventsPresenter.filterEvents((String) comboBox1.getSelectedItem(), textField6.getText());
            eventsPersTableModel = new EventsPersTableModel(eventsList);
            table.setModel(eventsPersTableModel);
            table.setAutoCreateRowSorter(true);
        }

        if(e.getSource() == BACKButton){
            this.dispose();
            new EventCoordView();
        }

        if(e.getSource() == SORTEVENTSButton){
            List<Events> events1 = new ArrayList<>();
            if(comboBox2.getSelectedItem().equals("scop")){
                events1 = eventsPresenter.sortEvents("scop");
            }
            else if(comboBox2.getSelectedItem().equals("locatie")){
                events1 = eventsPresenter.sortEvents("locatie");
            }
            eventsPersTableModel = new EventsPersTableModel(events1);
            table.setModel(eventsPersTableModel);
            table.setAutoCreateRowSorter(true);
        }

    }

    public static class EventsPersTableModel extends AbstractTableModel {

        private final String[] columns = {"ID", "Scop", "Locatie", "Data", "Nr Persoane"};
        private java.util.List<Events> eventsList;

        public EventsPersTableModel(List<Events> eventsList) {
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
