package com.cloudy9101.weltec.recipe;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface IngredientDao {
    @Query("SELECT * FROM ingredientModel")
    List<IngredientModel> getAll();

    @Insert
    void insertAll(IngredientModel... ingredients);

    @Insert
    void insert(IngredientModel ingredient);

    @Delete
    void delete(IngredientModel ingredient);
}