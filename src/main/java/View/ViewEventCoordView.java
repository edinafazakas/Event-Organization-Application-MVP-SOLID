package View;

import Model.EventCoord;
import Model.Events;
import Model.User;
import Presenter.EventCoordPresenter;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ViewEventCoordView extends JFrame implements ActionListener, ViewEventCoordViewInterface {
    private JTable table;
    private JButton VIEWEVENTCOORDONATORSButton;
    private JButton ADDEVENTCOORDONATORButton;
    private JButton EDITEVENTCOORDINATORButton;
    private JButton DELETEEVENTCOORDINATORButton;
    private JButton FINDEVENTCOORDINATORButton;
    private JButton BACKButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JPanel p;
    private JTextField textField6;
    private EventCoordPresenter eventCoordPresenter;
    private List<EventCoord> eventCoords;
    private  int selectedIndex;
    private EventCoord selectedEventCoord;
    private EventCoordTableModel eventCoordTableModel;

    public ViewEventCoordView() {
        this.setTitle("View Event Coords");
        this.setContentPane(p);
        this.setSize(new Dimension(1100, 800));
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.eventCoordPresenter = new EventCoordPresenter();
        eventCoords = new ArrayList<>();

        this.VIEWEVENTCOORDONATORSButton.addActionListener(this);
        this.DELETEEVENTCOORDINATORButton.addActionListener(this);
        this.EDITEVENTCOORDINATORButton.addActionListener(this);
        this.FINDEVENTCOORDINATORButton.addActionListener(this);
        this.ADDEVENTCOORDONATORButton.addActionListener(this);
        this.BACKButton.addActionListener(this);


        tableSelection();
        getTableContents(eventCoords);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == VIEWEVENTCOORDONATORSButton){
            eventCoords = getAllEventCoord();
            getTableContents(eventCoords);
        }

        if(e.getSource() == ADDEVENTCOORDONATORButton){
            eventCoordPresenter.createEventCoord(new User(textField2.getText(), textField3.getText(), textField4.getText(), "eventCoord",textField5.getText(), textField6.getText()));
        }

        if(e.getSource() == EDITEVENTCOORDINATORButton){
            eventCoordPresenter.modifyEventCoord(Long.valueOf(textField1.getText()), textField2.getText(), textField3.getText(), textField4.getText(), textField5.getText(), textField6.getText());
        }

        if(e.getSource() == DELETEEVENTCOORDINATORButton){
            eventCoordPresenter.deleteEventCoord(Long.valueOf(textField1.getText()));
        }

        if(e.getSource() == FINDEVENTCOORDINATORButton){
            EventCoord events1 = eventCoordPresenter.findEventCoord(Long.valueOf(textField1.getText()));
            textField1.setText(String.valueOf(events1.getIdEvent()));
            textField2.setText(events1.getUser().getName());
            textField3.setText(events1.getUser().getPhone());
            textField4.setText(events1.getUser().getEmail());
            textField5.setText(events1.getUser().getUsername());
            textField6.setText(events1.getUser().getPassword());
        }

        if(e.getSource() == BACKButton){
            this.dispose();
            new AdminView();
        }

    }

    @Override
    public List<EventCoord> getAllEventCoord() {
        return eventCoordPresenter.allEventCoord();
    }

    @Override
    public void getTableContents(List<EventCoord> eventCoords) {
        eventCoordTableModel = new EventCoordTableModel(eventCoords);
        table.setModel(eventCoordTableModel);
        table.setAutoCreateRowSorter(true);
    }

    @Override
    public void tableSelection() {
        table.getSelectionModel().addListSelectionListener(e -> {
            if (! table.getSelectionModel().isSelectionEmpty()){
                selectedIndex = table.convertRowIndexToModel(table.getSelectedRow());
                selectedEventCoord = eventCoords.get(selectedIndex);
                if(selectedEventCoord != null){
                    textField1.setText(String.valueOf(selectedEventCoord.getIdEvent()));
                    textField2.setText(selectedEventCoord.getUser().getName());
                    textField3.setText(selectedEventCoord.getUser().getPhone());
                    textField4.setText(selectedEventCoord.getUser().getEmail());
                    textField5.setText(selectedEventCoord.getUser().getUsername());
                    textField6.setText(selectedEventCoord.getUser().getPassword());
                }
            }
        });
    }

    public static class EventCoordTableModel extends AbstractTableModel {

        private final String[] columns = {"ID", "Name", "Phone", "Mail", "Username", "Password"};
        private java.util.List<EventCoord> eventCoords;

        public EventCoordTableModel(List<EventCoord> eventCoords) {
            this.eventCoords = eventCoords;
        }

        @Override
        public int getRowCount() {
            return eventCoords.size();
        }

        @Override
        public int getColumnCount() {
            return columns.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex) {
                case 0 -> eventCoords.get(rowIndex).getIdEvent();
                case 1 -> eventCoords.get(rowIndex).getUser().getName();
                case 2 -> eventCoords.get(rowIndex).getUser().getPhone();
                case 3 -> eventCoords.get(rowIndex).getUser().getEmail();
                case 4 -> eventCoords.get(rowIndex).getUser().getUsername();
                case 5 -> eventCoords.get(rowIndex).getUser().getPassword();
                default -> "-";
            };
        }
        @Override
        public String getColumnName(int column){
            return columns[column];
        }
    }
}
