package com.lukechenshui.blitz_request.validators;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

/**
 * Created by luke on 11/23/16.
 */
public class HttpMethodValidator implements IParameterValidator {
    @Override
    public void validate(String name, String value) throws ParameterException {
        String method = value;

        if (!(method.equals("GET") || method.equals("POST") || method.equals("PUT") || method.equals("DELETE"))) {
            throw new ParameterException("The " + name + " parameter must either be GET/POST/PUT/DELETE");
        }
    }
}
