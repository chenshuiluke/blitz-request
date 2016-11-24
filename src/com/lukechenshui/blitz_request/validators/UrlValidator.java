package com.lukechenshui.blitz_request.validators;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by luke on 11/23/16.
 */
public class UrlValidator implements IParameterValidator {
    @Override
    public void validate(String name, String value) throws ParameterException {
        String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        if (!matcher.matches()) {
            throw new ParameterException("The host you have specified is of an invalid format.");
        }

    }
}
