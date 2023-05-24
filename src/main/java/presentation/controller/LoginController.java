package presentation.controller;

import bll.ClientBLL;
import model.Client;
import presentation.Login;
import presentation.single_point_access.GUIFrameSinglePointAccess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private Login login;
    private ClientBLL clientBLL;

    public void startLogic() {
        login = new Login();
        clientBLL = new ClientBLL();

        GUIFrameSinglePointAccess.changePanel(login.getMainPanel(), "Login");
        Boolean employee = false;
        Boolean client = false;


        login.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OptionsLoginRegisterController optionsLoginRegisterController = new OptionsLoginRegisterController();
                optionsLoginRegisterController.startLogic();
            }
        });


        login.getLoginBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer ID = Integer.parseInt(login.getIDTextField().getText());
                String password = login.getPasswordTextField().getText();
                String user = login.getUserComboBox().getSelectedItem().toString();

                System.out.println(ID);
                System.out.println(password);
                System.out.println(user);
                if(login.getIDTextField().getText().isEmpty() || password.isEmpty() || user.isEmpty()){
                    GUIFrameSinglePointAccess.showDialogMessage("Invalid input!");
                }else{
                    try{
                        Client c = clientBLL.findClientById(ID);
                        if(user == "Employee"){
                            EmployeeOptionsController employeeOptionsController = new EmployeeOptionsController();
                            employeeOptionsController.startLogic();

                        }else{
                            PlaceOrderController placeOrderController = new PlaceOrderController();
                            placeOrderController.startLogic(ID);
                        }
                    }catch (IndexOutOfBoundsException exception){
                        GUIFrameSinglePointAccess.showDialogMessage("User not found!");
                        exception.printStackTrace();
                    }


                }

            }
        });
    }
}
