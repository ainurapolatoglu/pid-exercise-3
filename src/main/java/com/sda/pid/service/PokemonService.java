package com.sda.pid.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.pid.model.Pokemon;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokemonService {
    public static Map<String, String> getPokemonUrlMap () {
        Map<String, String> pokemonUrlMap = new HashMap<>();
        try {

            WebResource webResource = Client.create().resource("https://pokeapi.co/api/v2/pokemon/?offset=0&limit=964");
            ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);

            if (response.getStatus() != 200) {
                System.out.println("Failed : HTTP error code : " + response.getStatus());
            }

            String output = response.getEntity(String.class);
            Map<String, Object> resultMap = new ObjectMapper().readValue(output, new TypeReference<Map<String, Object>>() {});
            List<Map> pokemonMapList = (List<Map>) resultMap.get("results");
            for(Map pokemon : pokemonMapList) {
                pokemonUrlMap.put((String) pokemon.get("name"), (String) pokemon.get("url"));
            }

        } catch (Exception e) {
            System.out.println("An error has occured: " + e.getMessage());
        }
        return pokemonUrlMap;
    }

    public static Pokemon getPokemon(String url) {
        WebResource webResource = Client.create().resource(url);
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        String responseStr = response.getEntity(String.class);
        if (response.getStatus() != 200) {
            System.out.println("Failed : HTTP error code : " + response.getStatus());
            return null;
        }
        try {
            Pokemon pokemon = new ObjectMapper().readValue(responseStr, new TypeReference<Pokemon>() {});
            return  pokemon;
        } catch (JsonProcessingException e) {
            System.out.println("An error has occured: " + e.getMessage());
            return null;
        }

    }
}
