package com.lukechenshui.blitz_request;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.validators.PositiveInteger;
import com.lukechenshui.blitz_request.validators.HttpMethodValidator;
import com.lukechenshui.blitz_request.validators.UrlQueryValidator;
import com.lukechenshui.blitz_request.validators.UrlValidator;

/**
 * Created by luke on 11/23/16.
 */
public class Config {
    @Parameter(names = {"-u", "--url"}, description = "URL to stress test", validateWith = UrlValidator.class, required = true)
    private static String url;
    @Parameter(names = {"-m", "--method"}, description = "The HTTP method to use", validateWith = HttpMethodValidator.class, required = true)
    private static String method;
    @Parameter(names = {"-n", "--num"}, description = "The number of requests to make", validateWith = PositiveInteger.class, required = true)
    private static int numRequests;
    @Parameter(names = {"-t", "--threads"}, description = "The number of threads to use to make requests", validateWith = PositiveInteger.class)
    private static int numThreads;
    @Parameter(names = {"-uq", "--url-queries"}, description = "URL query data", validateWith = UrlQueryValidator.class)
    private static String urlQueries;
    @Parameter(names = {"-f", "--form-data"}, description = "form data", validateWith = UrlQueryValidator.class)
    private static String formData;
    @Parameter(names = {"-j", "--json-data"}, description = "json data", validateWith = UrlQueryValidator.class)
    private static String jsonData;
    @Parameter(names = {"-se" ,"--show-errors"}, description = "Show error messages")
    private static boolean showErrors;
    @Parameter(names = {"-ss", "--show-successes"}, description = "Show success messages")
    private static boolean showSuccesses;
    @Parameter(names = {"-nc", "--num-connections"}, description = "The maximum number of concurrent connections. This " +
            "is limited by the number of open files your OS allows.", validateWith = PositiveInteger.class)
    private static int numConcurrentConnections;
    public Config() {
        url = "";
        method = "GET";
        numRequests = 1;
        numThreads = 1;
        numConcurrentConnections = 1024;
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        Config.url = url;
    }

    public static String getMethod() {
        return method;
    }

    public static void setMethod(String method) {
        Config.method = method;
    }

    public static int getNumRequests() {
        return numRequests;
    }

    public static void setNumRequests(int numRequests) {
        Config.numRequests = numRequests;
    }

    public static int getNumThreads() {
        return numThreads;
    }

    public static void setNumThreads(int numThreads) {
        Config.numThreads = numThreads;
    }

    public static String getUrlQueries() {
        if (urlQueries != null) {
            urlQueries = urlQueries.replace("\'{", "{");
            urlQueries = urlQueries.replace("}\'", "}");
        }
        return urlQueries;
    }

    public static void setUrlQueries(String urlQueries) {
        Config.urlQueries = urlQueries;
    }

    public static String getFormData() {
        if (formData != null) {
            formData = formData.replace("\'{", "{");
            formData = formData.replace("}\'", "}");
        }
        return formData;
    }

    public static void setFormData(String formData) {
        Config.formData = formData;
    }

    public static boolean isShowErrors() {
        return showErrors;
    }

    public static void setShowErrors(boolean showErrors) {
        Config.showErrors = showErrors;
    }

    public static boolean isShowSuccesses() {
        return showSuccesses;
    }

    public static void setShowSuccesses(boolean showSuccesses) {
        Config.showSuccesses = showSuccesses;
    }

    public static int getNumConcurrentConnections() {
        return numConcurrentConnections;
    }

    public static void setNumConcurrentConnections(int numConcurrentConnections) {
        Config.numConcurrentConnections = numConcurrentConnections;
    }

    public static String getJsonData() {
        if (jsonData != null) {
            jsonData = jsonData.replace("\'{", "{");
            jsonData = jsonData.replace("}\'", "}");
        }
        return jsonData;
    }

    public static void setJsonData(String jsonData) {
        Config.jsonData = jsonData;
    }
}
