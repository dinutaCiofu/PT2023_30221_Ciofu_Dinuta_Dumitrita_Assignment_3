package bll.validators;

import model.Client;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class ClientAgeValidator implements Validator<Client> {
	private static final int MIN_AGE = 16;

	public void validate(Client t) {

		if (t.getAge() < MIN_AGE ) {
			throw new IllegalArgumentException("The Client Age limit is not respected!");
		}

	}

}
