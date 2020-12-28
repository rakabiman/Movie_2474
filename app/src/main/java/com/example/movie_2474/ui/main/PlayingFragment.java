package com.example.movie_2474.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movie_2474.ApiClient;
import com.example.movie_2474.ApiService;
import com.example.movie_2474.DetailActivity;
import com.example.movie_2474.MovieAdapter;
import com.example.movie_2474.OnItemClickCallback;
import com.example.movie_2474.R;
import com.example.movie_2474.model.Movie;
import com.example.movie_2474.model.MovieResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayingFragment extends Fragment {

    private ArrayList<Movie> listMovie = new ArrayList<>();
    private RecyclerView rvMovie;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playing, container, false);

        rvMovie = view.findViewById(R.id.rv_movie);
        rvMovie.setHasFixedSize(true);
        rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));

        ApiService service = ApiClient.getRetrofitInstance().create(ApiService.class);
        Call<MovieResponse> call = service.getPlayingMovie("d58845c9d53da3e216a0a21300e1e90a");
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                listMovie = response.body().getResults();

                MovieAdapter movieAdapter = new MovieAdapter(null, listMovie);
                rvMovie.setAdapter(movieAdapter);

                movieAdapter.setOnItemClickCallback(new OnItemClickCallback() {
                    @Override
                    public void onItemClicked(Movie movie) {
                        Intent moveIntent1 = new Intent(getActivity(), DetailActivity.class);
                        moveIntent1.putExtra(DetailActivity.ITEM_EXTRA, movie);
                        startActivity(moveIntent1);
                    }
                });
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });

        return view;
    }
}