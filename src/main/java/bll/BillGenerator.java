package bll;

import model.Client;
import model.Orders;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BillGenerator {
    private Orders order;

    public BillGenerator(Orders order) {
        this.order = order;
    }

    public void generateBill() {
        File bill = new File("bill_id_" + order.getID() + ".txt");

        try {
            bill.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ProductBLL productBLL = new ProductBLL();
        ClientBLL clientBLL = new ClientBLL();

        Integer quantity = order.getQuantity();
        Double price = productBLL.findProductById(order.getIdProduct()).getPrice();

        Client client = clientBLL.findClientById(order.getIdClient());
        try {
            FileWriter fileWriter = new FileWriter(bill);
            fileWriter.write("Bill no. " + order.getID() + "\n\n");
            fileWriter.write("Product details:\n");
            fileWriter.write("Name: " + productBLL.findProductById(order.getIdProduct()) + "\n");
            fileWriter.write("Quantity: " + quantity + "\n");
            fileWriter.write("Price: " + price + "\n");
            fileWriter.write("Total price: " + price * quantity + "\n\n\n");

            fileWriter.write("Customer details\n");
            fileWriter.write("Name: " + client.getFirstName() + " " + client.getLastName() + " \n");
            fileWriter.write("Email: " + client.getEmail() + "\n");
            fileWriter.write("Address: " + client.getAddress() + "\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
