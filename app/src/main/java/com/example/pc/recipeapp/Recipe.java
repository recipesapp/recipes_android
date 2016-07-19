package com.example.pc.recipeapp;

public class Recipe {
    private String name;

    public Recipe(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean contains(String recipeName){
        return name.toLowerCase().contains(recipeName.toLowerCase());
    }
}
