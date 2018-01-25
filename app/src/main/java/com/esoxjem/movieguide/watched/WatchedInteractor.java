package com.esoxjem.movieguide.watched;

import com.esoxjem.movieguide.Movie;

import java.util.List;

public interface WatchedInteractor
{
    void setWatched(Movie movie);
    boolean isWatched(String id);
    List<Movie> getWatched();
    void unWatched(String id);
}
