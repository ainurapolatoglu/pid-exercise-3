package com.sda.pid;


public class Pokemon {
    private String name; //attribute
    private String id; // the rest down are elements


    public Pokemon () {

    }

    public Pokemon(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("name: ");
        str.append(name);
        str.append(", id: ");
        str.append(id);
        return str.toString();
    }
}
