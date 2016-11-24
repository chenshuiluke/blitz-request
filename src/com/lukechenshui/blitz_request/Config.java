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
    public Config() {
        url = "";
        method = "GET";
        numRequests = 1;
        numThreads = 1;
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
}
