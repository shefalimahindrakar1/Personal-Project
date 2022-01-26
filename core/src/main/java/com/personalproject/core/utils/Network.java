package com.personalproject.core.utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class Network {


    public static String readJson(String url_passed) throws IOException {

        try {
            URL url = new URL(url_passed);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");

            InputStream responseStream = connection.getInputStream();

            String text = new BufferedReader(new InputStreamReader(responseStream, StandardCharsets.UTF_8)).lines().collect(Collectors.joining(""));

            System.out.println(text);
            return text;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }

    }
}

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Network {
    private static final String USER_AGENT = "Mozilla/5.0";

    public static String readJson(String url) {

        try {

            URL requestURL = new URL(url);

            HttpsURLConnection connection = (HttpsURLConnection) requestURL.openConnection();

            connection.setRequestMethod("GET");

            connection.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String inputLine;

                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {

                    response.append(inputLine);

                }

                in.close();

                return response.toString();

            }

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

        return "";

    }
}

