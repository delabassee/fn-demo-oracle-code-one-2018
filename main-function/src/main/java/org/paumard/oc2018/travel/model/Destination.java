package org.paumard.oc2018.travel.model;

import java.io.Serializable;
import java.util.Objects;

public class Destination implements Serializable {

    final long serialVersionUID = 1476176007496262807L;

    private String name;
    private int price;

    private String forecast;

    public Destination() {
    }

    public Destination(String name) {
        this.name = name;
    }

    public static Destination of(String name) {
        return new Destination(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getForecast() {
        return forecast;
    }

    public void setForecast(String forecast) {
        this.forecast = forecast;
    }

    public Destination withPrice(int price) {
        this.price = price;
        return this;
    }

    public Destination withForecast(String forecast) {
        this.forecast = forecast;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Destination that = (Destination) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Destination{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", forecast='" + forecast + '\'' +
                '}';
    }
}
