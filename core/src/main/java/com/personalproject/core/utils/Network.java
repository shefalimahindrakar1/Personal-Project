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
