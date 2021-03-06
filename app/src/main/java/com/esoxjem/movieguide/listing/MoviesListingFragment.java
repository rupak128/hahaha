package com.esoxjem.movieguide.listing;


import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.esoxjem.movieguide.BaseApplication;
import com.esoxjem.movieguide.Movie;
import com.esoxjem.movieguide.R;
import com.esoxjem.movieguide.listing.sorting.SortingDialogFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MoviesListingFragment extends Fragment implements MoviesListingView
{
    @Inject
    MoviesListingPresenter moviesPresenter;

    @BindView(R.id.movies_listing)
    RecyclerView moviesListing;

    @BindView(R.id.search)
    EditText editText;
    @BindView(R.id.search_button_active)
    Button button;

    @BindView(R.id.searchHolder)
    LinearLayout searchHolder;

    @BindView(R.id.expand)
    FrameLayout expand;

    private RecyclerView.Adapter adapter;
    private List<Movie> movies = new ArrayList<>(50);
    private Callback callback;
    private Unbinder unbinder;

    public MoviesListingFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        callback = (Callback) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);

        ((BaseApplication) getActivity().getApplication()).createListingComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_movies, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initLayoutReferences();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        moviesPresenter.setView(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_sort:
                displaySortingOptions();
        }

        return super.onOptionsItemSelected(item);
    }

    private void displaySortingOptions()
    {
        DialogFragment sortingDialogFragment = SortingDialogFragment.newInstance(moviesPresenter);
        sortingDialogFragment.show(getFragmentManager(), "Select Quantity");
    }

    private void initLayoutReferences()
    {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.d("see", "onClick: "+editText.getText());
//                Toast.makeText(getActivity().getBaseContext(),"Test",Toast.LENGTH_LONG).show();
                  expand.setVisibility(View.VISIBLE);
                  searchHolder.setVisibility(View.GONE);
                  moviesPresenter.displayMovies(editText.getText().toString().trim());
                  editText.setText("");
            }
        });

        expand.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                expand.setVisibility(View.GONE);
                searchHolder.setVisibility(View.VISIBLE);
                return false;
            }
        });



        moviesListing.setHasFixedSize(false);

        int columns;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            columns = 2;
        } else
        {
            columns = getResources().getInteger(R.integer.no_of_columns);
        }
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), columns);

        moviesListing.setLayoutManager(layoutManager);
        adapter = new MoviesListingAdapter(movies, this);
        moviesListing.setAdapter(adapter);
    }

    @Override
    public void showMovies(List<Movie> movies)
    {
        this.movies.clear();
        this.movies.addAll(movies);
        moviesListing.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
        callback.onMoviesLoaded(movies.get(0));
    }

    @Override
    public void loadingStarted()
    {
        Snackbar.make(moviesListing, R.string.loading_movies, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void loadingFailed(String errorMessage)
    {
        Snackbar.make(moviesListing, errorMessage, Snackbar.LENGTH_INDEFINITE).show();
    }

    @Override
    public void onMovieClicked(Movie movie)
    {

        callback.onMovieClicked(movie);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        moviesPresenter.destroy();
        unbinder.unbind();
    }

    @Override
    public void onDetach()
    {
        callback = null;
        super.onDetach();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        ((BaseApplication) getActivity().getApplication()).releaseListingComponent();
    }

    public interface Callback
    {
        void onMoviesLoaded(Movie movie);

        void onMovieClicked(Movie movie);
    }
}
