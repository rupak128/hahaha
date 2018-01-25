package com.esoxjem.movieguide.listing;

/**
 * @author arun
 */
public interface MoviesListingPresenter
{
    void displayMovies(String movieName);

    void setView(MoviesListingView view);

    void destroy();
}
