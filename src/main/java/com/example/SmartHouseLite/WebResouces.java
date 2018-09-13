package com.example.SmartHouseLite;

import com.example.SmartHouseLite.domain.TempSensorValue;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;


public class WebResouces {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String TEMP_SENSOR_URL = "192.168.1.199/getbmp280";

    public void turnArduinoSwitch(String addres) {
        try {
            sendGet(new URL("http://"+addres+"/gpio5/1"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public TempSensorValue getTempSensorVallue() {
        try {
            String data = sendGet(new URL(TEMP_SENSOR_URL));

            ObjectMapper objectMapper = new ObjectMapper();
            TempSensorValue tempSensorValue = objectMapper.readValue(data, TempSensorValue.class);
            Date date = new Date();
            tempSensorValue.setDate(new Timestamp(date.getTime()));
            return tempSensorValue;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (JsonParseException e) {
            e.printStackTrace();
            return null;
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String sendGet(URL url) {
        try {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            return response.toString();
        } catch (ProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String sendPost(URL url, String body) {
        try {
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(body);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            return response.toString();
        } catch (ProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
