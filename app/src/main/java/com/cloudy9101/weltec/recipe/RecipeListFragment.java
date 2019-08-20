package com.cloudy9101.weltec.recipe;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecipeListFragment extends Fragment {
    private Context context;
    private TextInputEditText searchText;
    private Button searchBtn;
    private Button intelligentBtn;
    private ListView listView;
    private ArrayList<RecipeModel> results = new ArrayList<>();
    private RecipeListAdapter adapter;

    public static Fragment newInstance() {
        Fragment frag = new RecipeListFragment();
        Bundle args = new Bundle();
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = this.getContext();
        setup(view);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    private void setup(View view) {
        searchText = view.findViewById(R.id.search);
        searchBtn = view.findViewById(R.id.searchBtn);
        intelligentBtn = view.findViewById(R.id.intelligentBtn);
        listView = view.findViewById(R.id.recipeList);

        search("");

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                search(searchText.getText().toString());
            }
        });

        intelligentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchByIngredients();
            }
        });
    }

    private void setupListView() {
        adapter = new RecipeListAdapter(this.getContext(), results);
        listView.setAdapter(adapter);
    }

    private void search(String query) {
        results = new ArrayList<>();
        ApiUtil.search(context, query, recipeResponseListener());
    }

    private String ingredients = "";
    private void searchByIngredients() {
        results = new ArrayList<>();
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                List<IngredientModel> ims = AppDatabase.db.ingredientDao().getAll();
                for(int i = 0; i < Math.min(ims.size(), 3); i++) {
                    ingredients += ims.get(i).getName() + ",";
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ApiUtil.searchByIngredients(context, ingredients, recipeByIngredientsResponseListener());
                    }
                });
            }
        });
    }

    private Response.Listener<String> recipeResponseListener() {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray res = obj.getJSONArray("results");
                    for(int i = 0; i < res.length(); i++) {
                        JSONObject item = res.getJSONObject(i);
                        RecipeModel rm = new RecipeModel();
                        rm.setId(item.getInt("id"));
                        rm.setImage(item.getString("image"));
                        rm.setTitle(item.getString("title"));
                        rm.setReadyInMinutes(item.getInt("readyInMinutes"));
                        results.add(rm);
                    }
                    setupListView();
                } catch(Exception error) {
                    System.out.println(error);
                }

            }
        };
    }


    private Response.Listener<String> recipeByIngredientsResponseListener() {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray res = new JSONArray(response);
                    for(int i = 0; i < res.length(); i++) {
                        JSONObject item = res.getJSONObject(i);
                        RecipeModel rm = new RecipeModel();
                        rm.setId(item.getInt("id"));
                        rm.setImage(item.getString("image"));
                        rm.setTitle(item.getString("title"));
                        results.add(rm);
                    }
                    setupListView();
                } catch(Exception error) {
                    System.out.println(error);
                }

            }
        };
    }
}
