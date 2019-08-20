package com.cloudy9101.weltec.recipe;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class IngredientModel {

    public IngredientModel(String name) {
        this.name = name;
    }

    @PrimaryKey
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

    @ColumnInfo(name = "name")
    private String name;
}
