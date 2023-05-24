package presentation.controller;

import bll.ClientBLL;

import model.Client;
import presentation.DisplayClients;
import presentation.single_point_access.GUIFrameSinglePointAccess;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DisplayClientsController {
    private DisplayClients displayClients;
    private ClientBLL clientBLL;

    public void startLogic(){
        displayClients = new DisplayClients();
        clientBLL = new ClientBLL();

        GUIFrameSinglePointAccess.changePanel(displayClients.getMainPanel(), "Display clients");
        JTable table = clientBLL.createTable();

        JScrollPane scrollPane = displayClients.getTableScrollPane();
        scrollPane.setViewportView(null);
        scrollPane.setViewportView(table);

        displayClients.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeOptionsController employeeOptionsController = new EmployeeOptionsController();
                employeeOptionsController.startLogic();
            }
        });
    }
}
