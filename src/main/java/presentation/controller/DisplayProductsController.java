package presentation.controller;

import bll.ProductBLL;
import presentation.DisplayProducts;
import presentation.single_point_access.GUIFrameSinglePointAccess;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayProductsController {
    private DisplayProducts displayProducts;
    private ProductBLL productBLL;

    public void startLogic(){
        displayProducts = new DisplayProducts();
        productBLL = new ProductBLL();

        GUIFrameSinglePointAccess.changePanel(displayProducts.getMainPanel(),"Display products");

        JTable table = productBLL.createTable();


        JScrollPane scrollPane = displayProducts.getScrollPane();
        scrollPane.setViewportView(null);
        scrollPane.setViewportView(table);

        displayProducts.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeOptionsController employeeOptionsController = new EmployeeOptionsController();
                employeeOptionsController.startLogic();
            }
        });
    }
}
