package bll.validators;

import java.util.regex.Pattern;

import model.Client;


public class PhoneNumberValidator implements Validator<Client> {
    private static final String PHONE_NUMBER_PATTERN = "^(\\+4|)?(07[0-8]{1}[0-9]{1}|02[0-9]{2}|03[0-9]{2}){1}?(\\s|\\.|\\-)?([0-9]{3}(\\s|\\.|\\-|)){2}$";

    @Override
    public void validate(Client c) {
        Pattern pattern = Pattern.compile(PHONE_NUMBER_PATTERN);
        if (!pattern.matcher(c.getPhoneNumber()).matches()) {
            throw new IllegalArgumentException("Phone number is nod valid!");
        }
    }
}
