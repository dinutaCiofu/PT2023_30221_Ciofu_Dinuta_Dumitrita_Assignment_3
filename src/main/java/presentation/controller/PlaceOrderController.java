package presentation.controller;

import bll.OrderBLL;
import bll.ProductBLL;
import dao.OrderDAO;
import model.Orders;
import model.Product;
import presentation.PlaceOrder;
import presentation.single_point_access.GUIFrameSinglePointAccess;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PlaceOrderController {
    private PlaceOrder order;
    private OrderBLL orderBLL;
    private ProductBLL productBLL;

    public void startLogic(Integer clientID) {
        order = new PlaceOrder();
        orderBLL = new OrderBLL();
        productBLL = new ProductBLL();
        GUIFrameSinglePointAccess.changePanel(order.getMainPanel(), "New order");

        List<Product> products = productBLL.findAllProducts();
        JList<String> productsJList = order.getProductsList();
        DefaultListModel<String> listModel = new DefaultListModel<>();

        for (Product product : products) {
            String value = product.getID() + ". " + product.getName() + ", price: " + product.getPrice()+", stock: "+product.getStock();
            listModel.addElement(value);
        }
        productsJList.setModel(listModel);


        order.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginController loginController = new LoginController();
                loginController.startLogic();
            }
        });

        order.getPlaceOrderButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedProduct = order.getProductsList().getSelectedValue().toString();
                Integer ID = Integer.parseInt(selectedProduct.substring(0, 1));
                Integer introducedQuantity = Integer.parseInt(order.getQuantityTextField().getText());
                Product product = productBLL.findProductById(ID);
                if (introducedQuantity > product.getStock()) {
                    GUIFrameSinglePointAccess.showDialogMessage("Invalid quantity! \nPlease check the stock of the product.");
                } else {
                    Integer newStock = product.getStock() - introducedQuantity;
                    Product updatedProduct = new Product(ID, product.getName(), product.getPrice(), newStock);

                    Orders order1;
                    List<Orders> ordersList = orderBLL.findAllOrders();
                    if (ordersList.isEmpty()) {
                        order1 = new Orders(1, clientID, product.getID(), introducedQuantity, product.getPrice() * introducedQuantity);
                        System.out.println("id is " + order1.getID());
                    } else {
                        Integer lastID = ordersList.get(ordersList.size() - 1).getID();
                        System.out.println(lastID + " last ID");
                        order1 = new Orders(lastID + 1, clientID, product.getID(), introducedQuantity, product.getPrice() * introducedQuantity);
                    }
                    orderBLL.addOrder(order1);

                    if (newStock == 0) {
                        productBLL.deleteProduct(ID);
                    } else {
                        productBLL.updateProduct(ID, updatedProduct);
                    }

                }

                PlaceOrderController placeOrderController = new PlaceOrderController();
                placeOrderController.startLogic(clientID);
            }
        });
    }

}
