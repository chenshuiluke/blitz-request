package com.lukechenshui.blitz_request;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.validators.PositiveInteger;
import com.lukechenshui.blitz_request.validators.HttpMethodValidator;
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
    @Parameter(names = {"-uq", "--url-queries"}, description = "URL query data")
    private static String urlQueries;
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
        urlQueries = "";
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
        return urlQueries;
    }

    public static void setUrlQueries(String urlQueries) {
        Config.urlQueries = urlQueries;
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
}
