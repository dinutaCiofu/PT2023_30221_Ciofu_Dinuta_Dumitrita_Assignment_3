package presentation.controller;

import bll.ClientBLL;
import bll.validators.Validator;
import model.Client;
import presentation.Register;
import presentation.single_point_access.GUIFrameSinglePointAccess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RegisterController {
    private Register register;
    private ClientBLL clientBLL;

    public void startLogic() {
        register = new Register();
        clientBLL = new ClientBLL();

        GUIFrameSinglePointAccess.changePanel(register.getMainPanel(), "New Client");

        register.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OptionsLoginRegisterController optionsLoginRegisterController = new OptionsLoginRegisterController();
                optionsLoginRegisterController.startLogic();
            }
        });

        register.getCreateAccountButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer ID = Integer.parseInt(register.getIDTextField().getText());
                String firstName = register.getFirstNameTextField().getText();
                String lastName = register.getLastNameTextField().getText();
                Integer age = Integer.parseInt(register.getAgeTextField().getText());
                String email = register.getEmailTextField().getText();
                String password = register.getPasswordTextField().getText();
                String phoneNumber = register.getPhoneNumberTextField().getText();
                String address = register.getAddressTextField().getText();

                if (firstName.isEmpty() || register.getIDTextField().getText().isEmpty() || lastName.isEmpty() || register.getAgeTextField().getText().isEmpty() || email.isEmpty() || password.isEmpty() || phoneNumber.isEmpty() || address.isEmpty()) {
                    GUIFrameSinglePointAccess.showDialogMessage("Invalid data!");
                } else {
                    Client newClient = new Client(ID, firstName, lastName, age, email, password, phoneNumber, address);

                    List<Validator<Client>> validators = clientBLL.getValidators();
                    Validator<Client> validateEmail = validators.get(0);
                    Validator<Client> validatePhoneNumber = validators.get(1);
                    Validator<Client> validateAge = validators.get(2);

                    try {
                        validateEmail.validate(newClient);
                    } catch (IllegalArgumentException ex) {
                        GUIFrameSinglePointAccess.showDialogMessage("Invalid Email!");
                    }
                    try {
                        validatePhoneNumber.validate(newClient);
                    } catch (IllegalArgumentException ex) {
                        GUIFrameSinglePointAccess.showDialogMessage("Invalid phone number!");
                    }
                    try {
                        validateAge.validate(newClient);
                    } catch (IllegalArgumentException ex) {
                        GUIFrameSinglePointAccess.showDialogMessage("Invalid age!");
                    }
                    clientBLL.addClient(newClient);

                    GUIFrameSinglePointAccess.showDialogMessage("Account created successfully!");

                    PlaceOrderController placeOrderController = new PlaceOrderController();
                    placeOrderController.startLogic(newClient.getID());

                }
            }
        });
    }
}
