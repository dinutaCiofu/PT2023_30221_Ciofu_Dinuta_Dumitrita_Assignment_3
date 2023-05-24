package presentation.controller;

import bll.ProductBLL;
import model.Product;
import presentation.AddProduct;
import presentation.single_point_access.GUIFrameSinglePointAccess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddProductController {
    private AddProduct addProduct;
    private ProductBLL productBLL;

    public void startLogic() {
        addProduct = new AddProduct();
        productBLL = new ProductBLL();

        GUIFrameSinglePointAccess.changePanel(addProduct.getMainPanel(), "New product");

        addProduct.getAddProductButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer ID = Integer.parseInt(addProduct.getIDTextField().getText());
                String name = addProduct.getNameTextField().getText();
                Double price = Double.valueOf(addProduct.getPriceTextField().getText());
                Integer stock = Integer.parseInt(addProduct.getStockTextField().getText());

                if (addProduct.getIDTextField().getText().isEmpty() || name.isEmpty() || addProduct.getPriceTextField().getText().isEmpty() || addProduct.getStockTextField().getText().isEmpty()) {
                    GUIFrameSinglePointAccess.showDialogMessage("Invalid data!");
                } else {
                    Product product = new Product(ID, name, price, stock);
                    productBLL.addProduct(product);
                    GUIFrameSinglePointAccess.showDialogMessage("Product added successfully!");
                }
                AddProductController addProductController = new AddProductController();
                addProductController.startLogic();

            }
        });

        addProduct.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeOptionsController employeeOptionsController = new EmployeeOptionsController();
                employeeOptionsController.startLogic();
            }
        });
    }
}
