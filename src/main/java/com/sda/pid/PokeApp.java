package com.sda.pid;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PokeApp {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int option = 0;
        System.out.println("Welcome to Poke App!");
        List<Pokemon> pokemons = new ArrayList<>();

        do {
            System.out.println("Pick an option:");
            System.out.println("(1) Show pokemon list.");
            System.out.println("(2) Enter a name of pokemon to see its id, height and weight.");
            System.out.println("(3) Enter a location.");
            System.out.println("(4) Quit.");

            option = Integer.parseInt(in.nextLine());

            if (option == 1) {  //Show members from file
                System.out.println("Pokemon was chosen");
                try {

                    Client client = Client.create();

                    WebResource webResource = client
                            .resource("https://pokeapi.co/api/v2/pokemon");

                    ClientResponse response = webResource.accept("application/json")
                            .get(ClientResponse.class);

                    if (response.getStatus() != 200) {
                        throw new RuntimeException("Failed : HTTP error code : "
                                + response.getStatus());
                    }

                    String output = (String) response.getEntity(String.class);

                    System.out.println("Output from Server .... \n");
                    System.out.println(output);

                } catch (Exception e) {

                    e.printStackTrace();

                }





            } else if (option == 2) { //Take attendance of each member and save into file
                System.out.println("Please enter location name.");
                String location = in.nextLine();

                if (!location.isEmpty()) {
                    try {
                        System.out.println("Location was chosen");
                    } catch (DateTimeParseException d) {
                        System.out.println("Entered date is in the wrong format. Default date was set.");
                    }
                }
                System.out.println(location.toString());

            } else if (option == 3) {
                System.out.println(" Thank you! Come again!");
                System.out.println(" :D ");
                break;
            } else {
                System.out.println("Entered option doesn't exist. Please enter correct option!");
            }
        } while (option > 0);
        in.close();
    }
}
