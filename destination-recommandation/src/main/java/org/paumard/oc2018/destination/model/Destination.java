package org.paumard.oc2018.destination.model;

public class Destination {

    public Destination() {
    }

    public Destination(String name) {
        this.name = name;
    }

    private String name;

    public static Destination of(String name) {
        return new Destination(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}