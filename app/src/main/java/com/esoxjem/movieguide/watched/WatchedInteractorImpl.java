package com.esoxjem.movieguide.watched;

import com.esoxjem.movieguide.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author arun
 */
class WatchedInteractorImpl implements WatchedInteractor
{
    private WatchedStore WatchedStore;

    WatchedInteractorImpl(WatchedStore store)
    {
        WatchedStore = store;
    }

    @Override
    public void setWatched(Movie movie)
    {
        WatchedStore.setWatched(movie);
    }

    @Override
    public boolean isWatched(String id)
    {
        return WatchedStore.isWatched(id);
    }

    @Override
    public List<Movie> getWatched() {
        return null;
    }

    public List<Movie> getWatchedList()
    {
        try
        {
            return WatchedStore.getWatched();
        } catch (IOException ignored)
        {
            return new ArrayList<>(0);
        }
    }

    @Override
    public void unWatched(String id)
    {
        WatchedStore.unWatched(id);
    }
}
