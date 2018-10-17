package org.paumard.oc2018.weather.model;

import javax.swing.*;
import java.io.Serializable;

public class Destination implements Serializable {

    final long serialVersionUID = 1476176007496262807L;

    private String name;
    private String forecast;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getForecast() {
        return forecast;
    }

    public void setForecast(String forecast) {
        this.forecast = forecast;
    }
}
