package com.lukechenshui.blitz_request;

import com.mashape.unirest.http.HttpResponse;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;

/**
 * Created by luke on 11/23/16.
 */
public class Status {
    public static int numErrors = 0;
    public static int numSuccesses = 0;
    public static int numCompletedRequests = 0;
    public static HashMap<Integer, String> errorResponses = new HashMap<>();
    public static HashMap<Integer, String> successResponses = new HashMap<>();
    public static double totalResponseTime = 0;
    public static double avgResponseTime = 0;
    public static long totalTimeTaken = 0;

    public static int getNumErrors() {
        return numErrors;
    }

    public static void setNumErrors(int numErrors) {
        Status.numErrors = numErrors;
    }

    public static int getNumSuccesses() {
        return numSuccesses;
    }

    public static void setNumSuccesses(int numSuccesses) {
        Status.numSuccesses = numSuccesses;
    }

    public static void incrementNumErrors() {
        numErrors++;
        numCompletedRequests++;
    }

    public static void incrementNumSuccesses() {
        numSuccesses++;
        numCompletedRequests++;
    }

    public static void addErrorResponse(HttpResponse response) {
        if (!errorResponses.containsValue(response.getBody().toString())) {
            errorResponses.put(numErrors, response.getBody().toString());
        }

        incrementNumErrors();
    }

    public static void addSuccessResponse(HttpResponse response) {
        if (!successResponses.containsValue(response.getBody().toString())) {
            successResponses.put(numSuccesses, response.getBody().toString());
        }

        incrementNumSuccesses();
    }

    public static double getTotalResponseTime() {
        return totalResponseTime;
    }

    public static void setTotalResponseTime(double totalResponseTime) {
        Status.totalResponseTime = totalResponseTime;
    }

    public static double getAvgResponseTime() {
        return avgResponseTime;
    }

    public static void setAvgResponseTime(double avgResponseTime) {
        Status.avgResponseTime = avgResponseTime;
    }

    public static void addResponseTime(double newResponseTime) {
        totalResponseTime += newResponseTime;
        avgResponseTime = totalResponseTime / numCompletedRequests;
    }

    public static long getTotalTimeTaken() {
        return totalTimeTaken;
    }

    public static void setTotalTimeTaken(long totalTimeTaken) {
        Status.totalTimeTaken = totalTimeTaken;
    }

    public static int getNumCompletedRequests() {
        return numCompletedRequests;
    }

    public static void report() {
        System.out.print("                                                                                          \r");
        DecimalFormat df = new DecimalFormat("#.###ms");
        df.setRoundingMode(RoundingMode.CEILING);
        StringBuffer report = new StringBuffer("URL: " + Config.getUrl() + " Method: " + Config.getMethod() +
                " Number of Requests: " + Config.getNumRequests() + "\n");
        report.append("Time taken: " + df.format(totalTimeTaken) + "\n");
        report.append("Number of Errors: " + numErrors + " (" + (numErrors / Config.getNumRequests()) * 100 + "%)\n");
        report.append("Number of Successes: " + numSuccesses + " (" + (numSuccesses / Config.getNumRequests()) * 100 + "%)\n");
        report.append("Avg. Response Time: " + df.format(avgResponseTime) + "\n");

        if (numSuccesses > 0 && Config.isShowSuccesses()) {
            report.append("Success Responses:\n");
            for (int counter = 0; counter < numSuccesses; counter++) {
                if (successResponses.get(counter) != null) {
                    report.append(successResponses.get(counter) + "\n");
                }
            }
        }

        if (numErrors > 0 && Config.isShowErrors()) {
            report.append("Error Responses:\n");
            for (int counter = 0; counter < numErrors; counter++) {
                if (errorResponses.get(counter) != null) {
                    report.append(errorResponses.get(counter) + "\n");
                }
            }
        }
        System.out.println(report);

    }
}
