package com.sda.pid.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {
    private String name; //attribute
    private Integer id; // the rest down are elements

    public Location(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public Location () {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
