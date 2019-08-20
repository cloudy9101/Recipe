package com.cloudy9101.weltec.recipe;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements IngredientsFragment.OnListFragmentInteractionListener {
    private Fragment frag;
    BottomNavigationView navView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            selectFragment(item);
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppDatabase.initDatabase(getApplicationContext());

        setContentView(R.layout.activity_main);
        navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        selectFragment(navView.getMenu().getItem(0));
    }

    private void selectFragment(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_recipes:
                frag = RecipeListFragment.newInstance();
                break;
            case R.id.navigation_ingredients:
                frag = IngredientsFragment.newInstance();
                break;
        }
        if (frag != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container, frag, frag.getTag());
            ft.commit();
        }
    }

    private IngredientModel itemWaitToDelete;
    public void onListFragmentInteraction(IngredientModel item, String action) {
        itemWaitToDelete = item;
        switch(action) {
            case "del":
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        AppDatabase.db.ingredientDao().delete(itemWaitToDelete);
                        if(navView.getSelectedItemId() == R.id.navigation_ingredients) {
                            ((IngredientsFragment) frag).setupListView();
                        }
                    }
                });
                break;
        }
    }
}