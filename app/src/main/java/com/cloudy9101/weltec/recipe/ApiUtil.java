package com.cloudy9101.weltec.recipe;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class ApiUtil {
    private final static String apiKey = "a53d1d9613064b95bcb03b86b6c52377";

    public static void search(Context context, String query, Response.Listener listener) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://api.spoonacular.com/recipes/search?apiKey=" + apiKey + "&query=" + query;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                listener
                , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                    }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public static void searchByIngredients(Context context, String ingredients, Response.Listener listener) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://api.spoonacular.com/recipes/findByIngredients?apiKey=" + apiKey + "&ingredients=" + ingredients + "&number=2";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                listener
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
