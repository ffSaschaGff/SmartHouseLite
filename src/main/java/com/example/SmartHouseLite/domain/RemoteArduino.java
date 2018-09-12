package com.example.SmartHouseLite.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Entity // This tells Hibernate to make a table out of this class
public class RemoteArduino {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String addres;

    RemoteArduino(){}

    public RemoteArduino(int id, String name, String addres) {
        this.id = id;
        this.name = name;
        this.addres = addres;
    }

    public RemoteArduino(String name, String addres){
        this.addres = addres;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return addres;
    }

    public void setaddres(String adress) {
        this.addres = addres;
    }

    public void turnSwitch() {
        try {
            URL myurl=new URL("http://"+this.addres+"/gpio5/1");
            HttpURLConnection  con = (HttpURLConnection) myurl.openConnection();

            con.setRequestMethod("GET");

            StringBuilder content;

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
