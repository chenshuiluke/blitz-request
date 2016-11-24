package com.lukechenshui.blitz_request.validators;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by luke on 11/23/16.
 */
public class UrlQueryValidator implements IParameterValidator {
    @Override
    public void validate(String name, String value) throws ParameterException {
        try {
            JSONObject jsonObj = new JSONObject(value);
        } catch (JSONException exc) {
            exc.printStackTrace();
            throw new ParameterException("The url query data you specified is not valid JSON.");
        }
    }
}
