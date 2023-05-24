package presentation.controller;

import bll.OrderBLL;
import presentation.DisplayOrders;
import presentation.single_point_access.GUIFrameSinglePointAccess;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayOrdersController {
    private DisplayOrders displayOrders;
    private OrderBLL orderBLL;

    public void startLogic(){
        displayOrders = new DisplayOrders();
        orderBLL = new OrderBLL();

        GUIFrameSinglePointAccess.changePanel(displayOrders.getMainPanel(), "Display orders");

        JTable table = orderBLL.createTable();

        JScrollPane scrollPane = displayOrders.getScrollPane();
        scrollPane.setViewportView(null);
        scrollPane.setViewportView(table);

        displayOrders.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeOptionsController employeeOptionsController = new EmployeeOptionsController();
                employeeOptionsController.startLogic();
            }
        });
    }
}
