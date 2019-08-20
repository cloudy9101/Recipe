package com.cloudy9101.weltec.recipe;

import java.util.ArrayList;
import java.util.List;

public class IngredientModel {

    public IngredientModel(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    static public Integer nextId = 0;

    public static void addItem(String name) {
        IngredientModel.ITEMS.add(new IngredientModel(IngredientModel.nextId, name));
        IngredientModel.nextId += 1;
    }

    public static void delItem(IngredientModel item) {
        IngredientModel.ITEMS.remove(item);
    }

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    static public List<IngredientModel> ITEMS = new ArrayList<>();
}
