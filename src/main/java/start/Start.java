package start;

import java.sql.SQLException;
import java.util.logging.Logger;

import presentation.controller.DisplayClientsController;
import presentation.controller.DisplayOrdersController;
import presentation.controller.EditProductController;
import presentation.controller.OptionsLoginRegisterController;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class Start {
	protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

	public static void main(String[] args) throws SQLException {

//		StudentBLL studentBll = new StudentBLL();
//
//		Student student1 = null;
//
//		try {
//			student1 = studentBll.findStudentById(1245);
//
//		} catch (Exception ex) {
//			LOGGER.log(Level.INFO, ex.getMessage());
//		}
//
//		// obtain field-value pairs for object through reflection
//		ReflectionExample.retrieveProperties(student1);


		OptionsLoginRegisterController optionsLoginRegisterController = new OptionsLoginRegisterController();
		optionsLoginRegisterController.startLogic();

//		DisplayClientsController displayClientsController = new DisplayClientsController();
//		displayClientsController.startLogic();

//		EditProductController editProductController = new EditProductController();
//		editProductController.startLogic();


	}

}
