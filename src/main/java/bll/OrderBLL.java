package bll;

import dao.OrderDAO;
import model.Bill;
import model.Orders;

import javax.swing.*;
import java.util.List;

public class OrderBLL {
    private OrderDAO orderDAO;


    public OrderBLL() {
        this.orderDAO = new OrderDAO();
    }



    public Orders findOrderById(Integer id) {
        Orders result = orderDAO.findById(id);
        if (result == null) {
            System.out.println("No order with id " + id + " was found");
        } else {
            System.out.println("Order with id " + id + " was found");
            System.out.println(result.toString());
        }
        return result;
    }

    public List<Orders> findAllOrders() {
        return orderDAO.findAll();
    }

    public void addOrder(Orders orderss) {
        List<Orders> orders = orderDAO.findAll();
        for (Orders o : orders) {
            if (o.getID() == orderss.getID()) {
                System.out.println("Order with id " + orderss.getID() + " already exists");
                throw new IllegalArgumentException("Order with id " + orderss.getID() + " already exists");
            }
        }
        orderDAO.insert(orderss);
        Bill bill = new Bill(orderss);
    }

    public void deleteOrder(Integer id) {
        Orders order = orderDAO.findById(id);
        orderDAO.delete(order.getID());
        System.out.println("Order with id " + order.getID() + " deleted.");
    }
    public JTable createTable (){
        List<Orders> orders = orderDAO.findAll();
        //findAll e bun
        JTable table = orderDAO.createTable(orders); //si aici se creeaza bine tabelul
        return table;
    }
}
