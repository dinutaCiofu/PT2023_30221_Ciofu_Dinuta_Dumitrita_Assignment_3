package presentation.controller;

import bll.ProductBLL;
import model.Product;
import presentation.EditProduct;
import presentation.single_point_access.GUIFrameSinglePointAccess;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EditProductController {
    private EditProduct editProduct;
    private ProductBLL productBLL;

    public void startLogic() {
        editProduct = new EditProduct();
        productBLL = new ProductBLL();
        GUIFrameSinglePointAccess.changePanel(editProduct.getMainPanel(), "Update");

        List<Product> products = productBLL.findAllProducts();
        JComboBox<String> productJComboBox = editProduct.getProductComboBox();
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

        for (Product product : products) {
            String value = product.getID() + " " + product.getName();
            comboBoxModel.addElement(value);
        }

        productJComboBox.setModel(comboBoxModel);


        editProduct.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeOptionsController employeeOptionsController = new EmployeeOptionsController();
                employeeOptionsController.startLogic();
            }
        });

        editProduct.getConfirmButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedProduct = editProduct.getProductComboBox().getSelectedItem().toString();
                Integer ID = Integer.parseInt(selectedProduct.substring(0, 1));

                Product product = productBLL.findProductById(ID);


                editProduct.getNameTextField().setText(product.getName());
                editProduct.getPriceTextField().setText(product.getPrice().toString());
                editProduct.getStockTextField().setText(product.getStock().toString());
            }
        });

        editProduct.getEditButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedProduct = editProduct.getProductComboBox().getSelectedItem().toString();
                Integer ID = Integer.parseInt(selectedProduct.substring(0, 1));


                String name = editProduct.getNameTextField().getText();
                Double price = Double.valueOf(editProduct.getPriceTextField().getText());
                Integer stock = Integer.parseInt(editProduct.getStockTextField().getText());

                Product updatedProduct = new Product(ID, name, price, stock);
                productBLL.updateProduct(ID, updatedProduct);

                // la final
                EditProductController editProductController = new EditProductController();
                editProductController.startLogic();
            }
        });

    }
}
