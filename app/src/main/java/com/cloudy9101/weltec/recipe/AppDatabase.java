package com.cloudy9101.weltec.recipe;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {IngredientModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract IngredientDao ingredientDao();

    public static AppDatabase db;

    public static void initDatabase(Context context) {
        db = Room.databaseBuilder(context, AppDatabase.class, "recipe_dev").build();
    }
}