package com.cloudy9101.weltec.recipe;

public class RecipeModel {
    private String title, image;
    private Integer id, readyInMinutes;

    public void setImage(String image) {
        this.image = image;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setReadyInMinutes(Integer readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    public String getImage() {
        return "https://spoonacular.com/recipeImages/" + image;
    }

    public Integer getId() {
        return id;
    }

    public Integer getReadyInMinutes() {
        return readyInMinutes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
