package View;

import Model.Client;
import Model.Events;
import Model.User;
import Presenter.ViewClientsPresenter;
import com.beust.ah.A;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ClientsPersAdminView extends JFrame implements ActionListener, ViewClientsViewInterface {
    private JPanel p;
    private JTable table;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JButton EDITCLIENTButton;
    private JButton VIEWCLIENTSButton;
    private JButton DELETECLIENTButton;
    private JButton FINDCLIENTButton;
    private JButton ADDCLIENTButton;
    private JButton BACKButton;
    private ViewClientsPresenter viewClientsPresenter;
    private List<Client> clients;
    private ClientsTableModel clientsTableModel;
    private int selectedIndex;
    private Client selectedClient;


    public ClientsPersAdminView() {
        this.setTitle("View Events");
        this.setContentPane(p);
        this.setSize(new Dimension(1010, 700));
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.viewClientsPresenter = new ViewClientsPresenter();
        clients = new ArrayList<>();

        this.VIEWCLIENTSButton.addActionListener(this);
        this.DELETECLIENTButton.addActionListener(this);
        this.EDITCLIENTButton.addActionListener(this);
        this.FINDCLIENTButton.addActionListener(this);
        this.ADDCLIENTButton.addActionListener(this);
        this.BACKButton.addActionListener(this);


        tableSelection();
        getTableContents(clients);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == VIEWCLIENTSButton){
            clients = viewClientsPresenter.allClients();
            getTableContents(clients);
        }

        if(e.getSource() == ADDCLIENTButton){
            viewClientsPresenter.createClient(new Client(new User(textField2.getText(), textField3.getText(), textField4.getText(), "client", textField5.getText(), textField6.getText())));
        }

        if(e.getSource() == EDITCLIENTButton){
            viewClientsPresenter.modifyClient(Long.valueOf(textField1.getText()), textField2.getText(), textField3.getText(), textField4.getText(), textField5.getText(), textField6.getText());
            System.out.println(textField1.getText());
        }

        if(e.getSource() == DELETECLIENTButton){
            viewClientsPresenter.deleteClient(Long.valueOf(textField1.getText()));
        }

        if(e.getSource() == FINDCLIENTButton){
            Client client = viewClientsPresenter.findClient(Long.valueOf(textField1.getText()));
            findClient(client);
        }

        if(e.getSource() == BACKButton){
            this.dispose();
            new AdminView();
        }
    }

    @Override
    public void tableSelection() {
        table.getSelectionModel().addListSelectionListener(e -> {
            if (! table.getSelectionModel().isSelectionEmpty()){
                selectedIndex = table.convertRowIndexToModel(table.getSelectedRow());
                selectedClient = clients.get(selectedIndex);
                if(selectedClient != null){
                    textField1.setText(selectedClient.getIdClient().toString());
                    textField2.setText(selectedClient.getUser().getName());
                    textField3.setText(selectedClient.getUser().getPhone());
                    textField4.setText(selectedClient.getUser().getEmail());
                    textField5.setText(selectedClient.getUser().getUsername());
                    textField6.setText(selectedClient.getUser().getPassword());
                }
            }
        });
    }

    @Override
    public void getTableContents(List<Client> clients) {
        clientsTableModel = new ClientsTableModel(clients);
        table.setModel(clientsTableModel);
        table.setAutoCreateRowSorter(true);
    }

    @Override
    public void findClient(Client client) {
        textField1.setText(String.valueOf(client.getIdClient()));
        textField2.setText(client.getUser().getName());
        textField3.setText(client.getUser().getPhone());
        textField4.setText(client.getUser().getEmail());
        textField5.setText(client.getUser().getUsername());
        textField6.setText(client.getUser().getPassword());
    }

    public static class ClientsTableModel extends AbstractTableModel {

        private final String[] columns = {"ID", "Name", "Phone", "Mail", "Username", "Password"};
        private List<Client> clientList;

        public ClientsTableModel(List<Client> clientList) {
            this.clientList = clientList;
        }

        @Override
        public int getRowCount() {
            return clientList.size();
        }

        @Override
        public int getColumnCount() {
            return columns.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex) {
                case 0 -> clientList.get(rowIndex).getIdClient();
                case 1 -> clientList.get(rowIndex).getUser().getName();
                case 2 -> clientList.get(rowIndex).getUser().getPhone();
                case 3 -> clientList.get(rowIndex).getUser().getEmail();
                case 4 -> clientList.get(rowIndex).getUser().getUsername();
                case 5 -> clientList.get(rowIndex).getUser().getPassword();

                default -> "-";
            };
        }
        @Override
        public String getColumnName(int column){
            return columns[column];
        }
    }
}
