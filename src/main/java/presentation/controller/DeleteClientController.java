package presentation.controller;

import bll.ClientBLL;
import model.Client;
import presentation.DeleteClient;
import presentation.single_point_access.GUIFrameSinglePointAccess;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DeleteClientController {
    private DeleteClient deleteClient;
    private ClientBLL clientBLL;


    public void startLogic() {
        deleteClient = new DeleteClient();
        clientBLL = new ClientBLL();

        GUIFrameSinglePointAccess.changePanel(deleteClient.getMainPanel(), "Delete user");

        List<Client> clients = clientBLL.getAllClients();
        JComboBox<String> clientsJComboBox = deleteClient.getComboBox1();
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

        for (Client client : clients) {
            String value = client.getID() + " " + client.getLastName() + " " + client.getFirstName();
            comboBoxModel.addElement(value);
        }

        clientsJComboBox.setModel(comboBoxModel);

        deleteClient.getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedClient = deleteClient.getComboBox1().getSelectedItem().toString();
                Integer ID = Integer.parseInt(selectedClient.substring(0,1));
                clientBLL.deleteClient(ID);

                DeleteClientController deleteClientController = new DeleteClientController();
                deleteClientController.startLogic();
            }
        });

        deleteClient.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeOptionsController employeeOptionsController = new EmployeeOptionsController();
                employeeOptionsController.startLogic();
            }
        });
    }

}
