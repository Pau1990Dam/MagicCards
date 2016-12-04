package com.pau.a14270729b.magiccards.HttpPetition;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class HttpUtils {
    public static String get(String dataUrl) throws IOException {

        URL url = new URL(dataUrl);
        String response = null;
        System.out.println(dataUrl);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            response = readStream(in);
        } finally {
            urlConnection.disconnect();
        }

        return response;
    }

    private static String readStream(InputStream in) throws IOException {

        InputStreamReader is = new InputStreamReader(in);
        BufferedReader rd = new BufferedReader(is);
        String line;
        StringBuilder response = new StringBuilder();

        while ((line = rd.readLine()) != null) {
            response.append(line);
            response.append('\r');
        }

        rd.close();

        return response.toString();
    }

    public static int getTotalCount(String dataUrl) throws IOException {

        URL url = new URL(dataUrl);
        int response;

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        Map<String, List<String>> map = urlConnection.getHeaderFields();
        response = Integer.parseInt(map.get("Total-Count").get(0));

        return response;
    }
}