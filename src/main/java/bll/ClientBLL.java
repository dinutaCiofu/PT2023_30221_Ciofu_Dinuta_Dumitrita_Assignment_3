package bll;

import bll.validators.ClientAgeValidator;
import bll.validators.EmailValidator;
import bll.validators.PhoneNumberValidator;
import bll.validators.Validator;
import dao.ClientDAO;
import model.Client;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ClientBLL {
    private ClientDAO clientDAO;
    private List<Validator<Client>> validators;

    public ClientBLL() {
        validators = new ArrayList<Validator<Client>>();
        validators.add(new EmailValidator());
        validators.add(new PhoneNumberValidator());
        validators.add(new ClientAgeValidator());
        clientDAO = new ClientDAO();
    }

    public List<Validator<Client>> getValidators() {
        return validators;
    }

    public Client findClientById(Integer id) {
        Client client = clientDAO.findById(id);
        if (client == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return client;
    }

    public List<Client> getAllClients() {
        return clientDAO.findAll();
    }

    public void addClient(Client client) {
        List<Client> clients = clientDAO.findAll();
        for (Client c : clients) {
            if (c.getID() == client.getID()) {
                throw new IllegalArgumentException("Client with id +" + client.getID() + " already exists!");
            }
        }
        clientDAO.insert(client);
    }

    public void deleteClient(Integer id) {
        Client client = clientDAO.findById(id);
        clientDAO.delete(client.getID());
        System.out.println("Client with id " + client.getID() + " deleted.");
    }

    public void updateClient(Integer id, Client newClient){
        Client client = clientDAO.findById(id);
        client = new Client(id,newClient.getFirstName(), newClient.getLastName(), newClient.getAge(), newClient.getEmail(), newClient.getPassword(), newClient.getPhoneNumber(), newClient.getAddress());
        clientDAO.update(client,client.getID());
    }

    public JTable createTable (){
        List<Client> clients = clientDAO.findAll();
       //findAll e bun
        JTable table = clientDAO.createTable(clients); //si aici se creeaza bine tabelul
        return table;
    }
}
