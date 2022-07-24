package com.devkuma.webflux;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.boot.json.JsonParser;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

@Service
public class HelloService {

    public String getLat()  {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("/home/ubuntu/lat.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return scanner.nextLine();

    }

    public String getLon() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("/home/ubuntu/lon.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return scanner.nextLine();
    }
}
