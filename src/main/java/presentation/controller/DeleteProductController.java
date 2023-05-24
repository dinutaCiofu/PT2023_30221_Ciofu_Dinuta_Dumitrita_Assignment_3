package presentation.controller;

import bll.ProductBLL;
import model.Product;
import presentation.DeleteProduct;
import presentation.single_point_access.GUIFrameSinglePointAccess;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DeleteProductController {
    private DeleteProduct deleteProduct;
    private ProductBLL productBLL;

    public void startLogic() {
        deleteProduct = new DeleteProduct();
        productBLL = new ProductBLL();
        GUIFrameSinglePointAccess.changePanel(deleteProduct.getMainPanel(), "Delete product");

        List<Product> products = productBLL.findAllProducts();
        JComboBox<String> productJComboBox = deleteProduct.getProductsComboBox();
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

        for (Product product : products) {
            String value = product.getID() + " " + product.getName();
            comboBoxModel.addElement(value);
        }

        productJComboBox.setModel(comboBoxModel);

        deleteProduct.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeOptionsController employeeOptionsController = new EmployeeOptionsController();
                employeeOptionsController.startLogic();
            }
        });

        deleteProduct.getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String selectedProduct = deleteProduct.getProductsComboBox().getSelectedItem().toString();
               Integer ID = Integer.parseInt(selectedProduct.substring(0,1));
               productBLL.deleteProduct(ID);

              DeleteProductController deleteProductController = new DeleteProductController();
              deleteProductController.startLogic();
            }
        });
    }
}
