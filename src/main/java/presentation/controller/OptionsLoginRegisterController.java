package presentation.controller;

import presentation.OptionsLoginRegister;
import presentation.single_point_access.GUIFrameSinglePointAccess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsLoginRegisterController {
    private OptionsLoginRegister optionsLoginRegister;
    public void startLogic(){
        optionsLoginRegister = new OptionsLoginRegister();
        GUIFrameSinglePointAccess.changePanel(optionsLoginRegister.getMainPanel(), "Login options");


        optionsLoginRegister.getLoginBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginController loginController = new LoginController();
                loginController.startLogic();
            }
        });

        optionsLoginRegister.getRegisterBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterController registerController = new RegisterController();
                registerController.startLogic();
            }
        });
    }
}
