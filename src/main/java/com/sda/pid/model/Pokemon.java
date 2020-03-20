package com.sda.pid.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokemon {
    private String name; //attribute
    private Integer id; // the rest down are elements
    private Integer height;
    private Integer weight;

    public Pokemon () {

    }

    public Pokemon(String name, Integer id, Integer height, Integer weight) {
        this.name = name;
        this.id = id;
        this.height = height;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("name: ");
        str.append(name);
        str.append(", id: ");
        str.append(id);
        str.append(", height: ");
        str.append(height);
        str.append(", weight: ");
        str.append(weight);
        return str.toString();
    }
}
