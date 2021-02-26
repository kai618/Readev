package com.kai.readev.chap7;

public class Drink {
    private final String name;
    private final String description;
    private final int imageResId;
    private final boolean favorite;

    public Drink(String name, String description, int imageResId) {
        this.name = name;
        this.description = description;
        this.imageResId = imageResId;
        this.favorite = false;
    }

    public Drink(String name, String description, int imageResId, boolean favorite) {
        this.name = name;
        this.description = description;
        this.imageResId = imageResId;
        this.favorite = favorite;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getDescription() {
        return description;
    }

    public boolean getFavorite() {
        return favorite;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
