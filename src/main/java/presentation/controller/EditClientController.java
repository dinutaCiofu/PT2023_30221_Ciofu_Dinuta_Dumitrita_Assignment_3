package presentation.controller;

import bll.ClientBLL;
import model.Client;

import presentation.EditClient;
import presentation.single_point_access.GUIFrameSinglePointAccess;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EditClientController {
    private EditClient editClient;
    private ClientBLL clientBLL;

    public void startLogic() {
        editClient = new EditClient();
        clientBLL = new ClientBLL();
        GUIFrameSinglePointAccess.changePanel(editClient.getMainPanel(), "Update");

        List<Client> clients = clientBLL.getAllClients();
        JComboBox<String> clientsComboBox = editClient.getComboBox1();
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();


        for (Client client : clients) {
            String value = client.getID() + " " + client.getFirstName() + " " + client.getLastName();
            comboBoxModel.addElement(value);
        }

        clientsComboBox.setModel(comboBoxModel);

        editClient.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeOptionsController employeeOptionsController = new EmployeeOptionsController();
                employeeOptionsController.startLogic();
            }
        });

        editClient.getSelectButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String selectedClient = editClient.getComboBox1().getSelectedItem().toString();
                Integer ID = Integer.parseInt(selectedClient.substring(0, 1));

                Client client = clientBLL.findClientById(ID);

                editClient.getFirstNameTextField().setText(client.getFirstName());
                editClient.getLastNameTextField().setText(client.getLastName());
                editClient.getAgeTextField().setText(client.getAge().toString());
                editClient.getEmailTextField().setText(client.getEmail());
                editClient.getPasswordTextField().setText(client.getPassword());
                editClient.getPhoneNumberTextField().setText(client.getPhoneNumber());
                editClient.getAddressTextField().setText(client.getAddress());
            }
        });

        editClient.getEditButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedClient = editClient.getComboBox1().getSelectedItem().toString();
                Integer ID = Integer.parseInt(selectedClient.substring(0, 1));


                String firstName = editClient.getFirstNameTextField().getText();
                String lastName = editClient.getLastNameTextField().getText();
                Integer age = Integer.parseInt(editClient.getAgeTextField().getText());
                String email = editClient.getEmailTextField().getText();
                String password = editClient.getPasswordTextField().getText();
                String phoneNumber = editClient.getPhoneNumberTextField().getText();
                String address = editClient.getAddressTextField().getText();

                Client updatedClient = new Client(ID, firstName, lastName, age, email, password, phoneNumber, address);
                clientBLL.updateClient(ID, updatedClient);

                EditClientController editClientController = new EditClientController();
                editClientController.startLogic();
            }
        });
    }
}
