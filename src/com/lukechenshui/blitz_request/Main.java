package com.lukechenshui.blitz_request;

import com.beust.jcommander.JCommander;
import com.mashape.unirest.http.HttpMethod;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    static Lock lock = new ReentrantLock();
    static long overallStartTime = 0;
    static Thread statusOutputThread = new Thread(new Runnable() {
        @Override
        public void run() {

            while (true) {
                lock.lock();
                DecimalFormat df = new DecimalFormat("#.###ms");
                df.setRoundingMode(RoundingMode.CEILING);
                System.out.print("                                                                                          \r");
                long currentTime = System.currentTimeMillis();
                System.out.print("No. completed requests: " + Status.getNumCompletedRequests() + " / " +
                        Config.getNumRequests() + " No. errors: " +
                        Status.getNumErrors() + " No. successes: " + Status.getNumSuccesses() + " Runtime: "
                        + String.valueOf(currentTime - overallStartTime)
                        + " Avg. response time: " + df.format(Status.getAvgResponseTime()));
                lock.unlock();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException exc) {
                    exc.printStackTrace();
                }

            }
        }
    });

    public static void main(String[] args) {
        Config config = new Config();
        new JCommander(config, args);
        sendRequest();
        Status.report();
    }

    public static void sendRequest() {
        ExecutorService executor = Executors.newFixedThreadPool(Config.getNumThreads());

        overallStartTime = System.currentTimeMillis();
        statusOutputThread.start();
        for (int counter = 0; counter < Config.getNumRequests(); counter++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpRequest request = new HttpRequest(HttpMethod.GET, Config.getUrl());
                    switch (Config.getMethod()) {
                        case "GET":
                            request = Unirest.get(Config.getUrl());
                            break;
                        case "POST":
                            request = Unirest.post(Config.getUrl());
                            break;
                        case "PUT":
                            request = Unirest.put(Config.getUrl());
                            break;
                        case "DELETE":
                            request = Unirest.delete(Config.getUrl());
                            break;
                        case "DEFAULT":
                            throw new IllegalStateException();
                    }
                    try {
                        long startTime = System.currentTimeMillis();
                        HttpResponse response = request.asString();
                        long endTime = System.currentTimeMillis();
                        long elapsedTime = endTime - startTime;
                        lock.lock();
                        if (response.getStatus() != 200) {
                            Status.addErrorResponse(response);
                        } else {
                            Status.incrementNumSuccesses();
                        }
                        Status.addResponseTime((double) elapsedTime);
                        lock.unlock();
                    } catch (UnirestException exc) {
                        exc.printStackTrace();
                    }
                }
            });
            executor.execute(thread);
        }
        executor.shutdown();
        while (!executor.isTerminated()) ;
        long overallEndTime = System.currentTimeMillis();
        Status.setTotalTimeTaken(overallEndTime - overallStartTime);
        statusOutputThread.stop();
    }
}
