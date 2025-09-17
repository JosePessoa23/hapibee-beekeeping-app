package com.isep.acme.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
public class httpRequest {

    public void executeHttpRequest(String apiarioid,double latitude,double longitude){
        try {
            // Specify the URL of the endpoint
            String endpointUrl = "http://localhost:8080/transumancia/" + apiarioid + "/" + latitude + "/" + longitude;

            // Create a URL object
            URL url = new URL(endpointUrl);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the HTTP method (GET, POST, etc.)
            connection.setRequestMethod("PUT");

            // Optional: Set request headers
            connection.setRequestProperty("Content-Type", "application/json");

            // Get the HTTP response code
            int responseCode = connection.getResponseCode();

            // Read the response from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            // Close the connection
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void executeHttpRequestApiario(Apiario apiario,String username){
        try {
            // Specify the URL of the endpoint
            String endpointUrl = "http://localhost:8080/apiarios/" + username ;

            // Create a URL object
            URL url = new URL(endpointUrl);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the HTTP method (GET, POST, etc.)
            connection.setRequestMethod("POST");

            // Optional: Set request headers
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setDoOutput(true);

            ObjectMapper objectMapper = new ObjectMapper();
            String apiarioJson = objectMapper.writeValueAsString(apiario.toDTOCreate());
            System.out.println(apiarioJson);

            // Write JSON to the connection output stream
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = apiarioJson.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Get the HTTP response code
            int responseCode = connection.getResponseCode();

            // Read the response from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            // Close the connection
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
