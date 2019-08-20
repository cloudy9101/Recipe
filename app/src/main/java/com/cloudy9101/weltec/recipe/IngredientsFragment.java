package com.cloudy9101.weltec.recipe;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class IngredientsFragment extends Fragment {

    private Context context;
    private OnListFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private Button addBtn;
    private TextInputEditText ingredientInput;
    private List<IngredientModel> ingredients;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public IngredientsFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static IngredientsFragment newInstance() {
        IngredientsFragment fragment = new IngredientsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = this.getContext();
        setup(view);
    }

    private void setup(View view) {
        recyclerView = view.findViewById(R.id.ingredientList);
        addBtn = view.findViewById(R.id.addBtn);
        ingredientInput = view.findViewById(R.id.ingredientInput);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new IngredientsRecyclerViewAdapter(new ArrayList<IngredientModel>(), mListener));
        setupListView();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        IngredientModel im = new IngredientModel(ingredientInput.getText().toString());
                        AppDatabase.db.ingredientDao().insert(im);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ingredientInput.setText("");
                            }
                        });
                        setupListView();
                    }
                });
            }
        });
    }

    public void setupListView() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                ingredients = AppDatabase.db.ingredientDao().getAll();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                        recyclerView.setAdapter(new IngredientsRecyclerViewAdapter(ingredients, mListener));
                    }
                });
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ingredients, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(IngredientModel item, String action);
    }
}
