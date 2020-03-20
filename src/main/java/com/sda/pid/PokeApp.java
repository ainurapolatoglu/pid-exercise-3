package com.sda.pid;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.pid.model.Pokemon;
import com.sda.pid.service.LocationService;
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
        Map<String, String> locationUrlMap = LocationService.getLocationUrlMap();
        do {
            System.out.println("Pick an option:");
            System.out.println("(1) Show pokemon list.");
            System.out.println("(2) Enter a name of pokemon to see its id, height and weight.");
            System.out.println("(3) Print all locations.");
            System.out.println("(4) Enter a location.");
            System.out.println("(5) Quit.");

            option = Integer.parseInt(in.nextLine());

            if (option == 1) {  //Show members from file
                System.out.println("Here is a list of all pokemons");
                System.out.println(pokemonUrlMap.keySet());

            } else if (option == 2) { // Write name of pokemon
                System.out.println("Please enter pokemon name:");
                String pokemonName = in.nextLine();
                String url = pokemonUrlMap.get(pokemonName);
                if (url == null){
                    System.out.println("Entered pokemon does not exist!");
                } else {
                    System.out.println(PokemonService.getPokemon(url));
                }

            } else if (option == 3) {  // Print all locations
                System.out.println("Here is a list of all pokemons");
                System.out.println(locationUrlMap.keySet());

            } else if (option == 4) {
                System.out.println("Please enter a location name.");
                String locationName = in.nextLine();
                String name = locationUrlMap.get(locationName);

                if (locationName == null) {
                        System.out.println("Wrong location ");
                } else {
                    System.out.println(LocationService.getLocation(locationName));
                    System.out.println("Feature under development. ");
                }

            } else if (option == 5) { // Quit
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
