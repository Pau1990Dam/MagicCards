package com.pau.a14270729b.magiccards.Pojos;

import java.io.Serializable;

public class Card implements Serializable {

    private String name;
    private String rarity;
    private String text;
    private String flavor;
    private String type;
    private String imageUrl;
    private String id;
    private String colors;
    private String power;
    private String toughness;
    private int cmc;


    public Card(){}

    public String getFlavor() { return flavor; }

    public void setFlavor(String flavor) { this.flavor = flavor; }

    public String getToughness() { return toughness; }

    public void setToughness(String toughness) { this.toughness = toughness; }

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
                ", flavor='" + flavor + '\'' +
                ", type='" + type + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", id='" + id + '\'' +
                ", colors='" + colors + '\'' +
                ", power='" + power + '\'' +
                ", toughness='" + toughness + '\'' +
                ", cmc=" + cmc +
                '}';
    }
}
