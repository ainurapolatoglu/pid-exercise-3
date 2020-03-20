package com.sda.pid.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.pid.model.Location;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationService {
    public static Map<String, String> getLocationUrlMap () {
        Map<String, String> locationUrlMap = new HashMap<>();
        try {
            WebResource webResource = Client.create().resource("https://pokeapi.co/api/v2/location/?offset=0&limit=781");
            ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);

            if (response.getStatus() != 200) {
                System.out.println("Failed : HTTP error code : " + response.getStatus());
            }

            String output = response.getEntity(String.class);
            Map<String, Object> resultMap = new ObjectMapper().readValue(output, new TypeReference<Map<String, Object>>() {});
            List<Map> locationMapList = (List<Map>) resultMap.get("results");
            for(Map location : locationMapList) {
                locationUrlMap.put((String) location.get("name"), (String) location.get("url"));
            }

        } catch (Exception e) {
            System.out.println("An error has occured: " + e.getMessage());
        }
        return locationUrlMap;
    }
    public static Location getLocation(String name) {
        WebResource webResource = Client.create().resource(name);
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        String responseStr = response.getEntity(String.class);
        if (response.getStatus() != 200) {
            System.out.println("Failed : HTTP error code : " + response.getStatus());
            return null;
        }
        try {
            Location location = new ObjectMapper().readValue(responseStr, new TypeReference<Location>() {});
            return  location;
        } catch (JsonProcessingException e) {
            System.out.println("An error has occured: " + e.getMessage());
            return null;
        }

    }
}
