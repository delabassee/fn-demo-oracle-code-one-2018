package org.paumard.oc2018.quotation.model;

import java.io.Serializable;

public class Destination implements Serializable {

    final long serialVersionUID = 1476176007496262807L;

    private String name;
    private int price;

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
}
