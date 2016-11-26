package com.pau.a14270729b.magiccards.Pojos;

import java.io.Serializable;

/**
 * Created by 14270729b on 14/10/16.
 */

public class Card implements Serializable {

    String name;
    String rarity;
    String text;
    String type;
    String imageUrl;
    String id;
    String colors;
    String power;
    int cmc;


    public Card(){}

    public Card(String name, String rarity, String text, String type, String imageUrl, String id, String colors, String power, int cmc) {
        this.name = name;
        this.rarity = rarity;
        this.text = text;
        this.type = type;
        this.imageUrl = imageUrl;
        this.id = id;
        this.colors = colors;
        this.power = power;
        this.cmc = cmc;
    }

    public String getPower() { return power; }

    public void setPower(String power) { this.power = power; }

    public int getCmc() { return cmc; }

    public void setCmc(int cmc) { this.cmc = cmc; }

    public String getColors() { return colors; }

    public void setColors(String colors) { this.colors = colors; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", rarity='" + rarity + '\'' +
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", id='" + id + '\'' +
                ", colors=" + colors +
                ", power='" + power + '\'' +
                ", cmc=" + cmc +
                '}';
    }
}
