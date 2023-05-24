package presentation.controller;

import bll.ProductBLL;
import model.Product;
import presentation.DisplayProducts;
import presentation.EmployeeOptions;
import presentation.single_point_access.GUIFrameSinglePointAccess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EnumMap;
import java.util.Queue;

public class EmployeeOptionsController {
    private EmployeeOptions employeeOptions;
    private ProductBLL productBLL;

    public void startLogic() {
        employeeOptions = new EmployeeOptions();
        productBLL = new ProductBLL();

        GUIFrameSinglePointAccess.changePanel(employeeOptions.getMainPanel(), "Employee options");

        employeeOptions.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginController loginController = new LoginController();
                loginController.startLogic();
            }
        });

        employeeOptions.getShowAllClientsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DisplayClientsController displayClientsController = new DisplayClientsController();
                displayClientsController.startLogic();
            }
        });

        employeeOptions.getShowAllOrdersButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DisplayOrdersController displayOrdersController = new DisplayOrdersController();
                displayOrdersController.startLogic();
            }
        });

        employeeOptions.getShowAllProductsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DisplayProductsController displayProductsController = new DisplayProductsController();
                displayProductsController.startLogic();
            }
        });

        employeeOptions.getAddAProductButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddProductController addProductController = new AddProductController();
                addProductController.startLogic();
            }
        });

        employeeOptions.getDeleteAProductButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteProductController deleteProductController = new DeleteProductController();
                deleteProductController.startLogic();
            }
        });

        employeeOptions.getDeleteAClientButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteClientController deleteClientController = new DeleteClientController();
                deleteClientController.startLogic();
            }
        });

        employeeOptions.getUpdateAClientButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                EditClientController editClientController = new EditClientController();
                editClientController.startLogic();
            }
        });

        employeeOptions.getUpdateAProductButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditProductController editProductController = new EditProductController();
                editProductController.startLogic();
            }
        });
    }
}
