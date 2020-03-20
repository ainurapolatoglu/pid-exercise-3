package com.sda.pid;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.pid.model.Pokemon;
import com.sda.pid.service.PokemonService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


import javax.ws.rs.core.MediaType;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PokeApp {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int option = 0;
        System.out.println("Welcome to Poke App!");
        List<Pokemon> pokemons = new ArrayList<>();

        Client client = Client.create();
        Map<String, String> pokemonUrlMap = PokemonService.getPokemonUrlMap();
        do {
            System.out.println("Pick an option:");
            System.out.println("(1) Show pokemon list.");
            System.out.println("(2) Enter a name of pokemon to see its id, height and weight.");
            System.out.println("(3) Enter a location.");
            System.out.println("(4) Quit.");

            option = Integer.parseInt(in.nextLine());

            if (option == 1) {  //Show members from file
                System.out.println("Here is a list of all pokemons");
                System.out.println(pokemonUrlMap.keySet());

            } else if (option == 2) { // Write name of pokemon
                System.out.println("Please enter pokemon name:");
                String pokemonName = in.nextLine();
                String url = pokemonUrlMap.get(pokemonName);

                WebResource webResource = client.resource(url);

                ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
                String responseStr = response.getEntity(String.class);
                if (response.getStatus() != 200) {
                    System.out.println("Failed : HTTP error code : " + response.getStatus());
                    continue;
                }
                try {
                    Pokemon pokemon = new ObjectMapper().readValue(responseStr, new TypeReference<Pokemon>() {});
                    System.out.println(pokemon);
                } catch (JsonProcessingException e) {
                    System.out.println("An error has occured: " + e.getMessage());
                }

            } else if (option == 3) {  // Enter location
                System.out.println("Please enter location name.");
                String location = in.nextLine();

                if (!location.isEmpty()) {
                    try {
                        System.out.println("Location was chosen");
                    } catch (DateTimeParseException d) {
                        System.out.println("Entered date is in the wrong format. Default date was set.");
                    }
                    System.out.println(location.toString());
                }

            } else if (option == 4) { // Quit
                System.out.println(" Thank you! Come again!");
                System.out.println(" :D ");
                break;
            } else {
                System.out.println("Entered option doesn't exist. Please enter correct option!");
            }
        } while (option > 0);
        in.close();
        client.destroy();
    }
}
