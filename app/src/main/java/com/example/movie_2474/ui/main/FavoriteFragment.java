package com.example.movie_2474.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.movie_2474.DatabaseHelper;
import com.example.movie_2474.MovieAdapter;
import com.example.movie_2474.R;
import com.example.movie_2474.model.Movie;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {

    RecyclerView rvMovieFav;
    private ArrayList<Movie> listMovie;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        rvMovieFav = view.findViewById(R.id.rv_movie_fav);

        rvMovieFav.setLayoutManager(new LinearLayoutManager(getActivity()));

        DatabaseHelper db = new DatabaseHelper(getActivity());
        listMovie = db.getAllMovie();

        if (listMovie.size() != 0) {
            MovieAdapter adapter = new MovieAdapter(getActivity(), listMovie);
            rvMovieFav.setAdapter(adapter);
        }

        return view;
    }
}