package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

/**
 * @Author: Ciofu Dinuta-Dumitrita
 * TP Assignment3
 * @Since: May 19, 2023
 */

public record Bill(Orders o) {
    /**
     * This is the constructor that generates the Bill.txt
     *
     * @param o The order that was added successfully
     */
    public Bill(Orders o) {
        this.o = new Orders(o.getID(),o.getIdClient(),o.getIdProduct(),o.getQuantity(),o.getPrice());
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        try {
            FileWriter fileWriter = new FileWriter("Order" + o.getID() + ".txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(timeStamp + "\n\n\n");
            printWriter.println("Order ID: " + o.getID());
            printWriter.println("Client ID: " + o.getIdClient());
            printWriter.println("Product ID: " + o.getIdProduct());
            printWriter.println("Quantity: " + o.getQuantity());
            printWriter.println("Total Price: " + o.getPrice());
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}