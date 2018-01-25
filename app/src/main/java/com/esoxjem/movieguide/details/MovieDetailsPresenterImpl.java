package com.esoxjem.movieguide.details;

import android.util.Log;

import com.esoxjem.movieguide.Movie;
import com.esoxjem.movieguide.Review;
import com.esoxjem.movieguide.Video;
import com.esoxjem.movieguide.favorites.FavoritesInteractor;
import com.esoxjem.movieguide.util.RxUtils;
import com.esoxjem.movieguide.watched.WatchedInteractor;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


class MovieDetailsPresenterImpl implements MovieDetailsPresenter {
    private MovieDetailsView view;
    private MovieDetailsInteractor movieDetailsInteractor;
    private FavoritesInteractor favoritesInteractor;
    private WatchedInteractor watchedInteractor;
    private Disposable trailersSubscription;
    private Disposable reviewSubscription;

    MovieDetailsPresenterImpl(MovieDetailsInteractor movieDetailsInteractor, FavoritesInteractor favoritesInteractor) {
        this.movieDetailsInteractor = movieDetailsInteractor;
        this.favoritesInteractor = favoritesInteractor;
       // this.watchedInteractor=watchedInteractor;
    }

    @Override
    public void setView(MovieDetailsView view) {
        this.view = view;
    }

    @Override
    public void destroy() {
        view = null;
        RxUtils.unsubscribe(trailersSubscription, reviewSubscription);
    }

    @Override
    public void showDetails(Movie movie) {
        if (isViewAttached()) {
            view.showDetails(movie);
        }
    }

    private boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void showTrailers(Movie movie) {
        trailersSubscription = movieDetailsInteractor.getTrailers(movie.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onGetTrailersSuccess, t -> onGetTrailersFailure());
    }

    private void onGetTrailersSuccess(List<Video> videos) {
        if (isViewAttached()) {
            view.showTrailers(videos);
        }
    }

    private void onGetTrailersFailure() {
        // Do nothing
    }

    @Override
    public void showReviews(Movie movie) {
        reviewSubscription = movieDetailsInteractor.getReviews(movie.getId()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onGetReviewsSuccess, t -> onGetReviewsFailure());
    }

    private void onGetReviewsSuccess(List<Review> reviews) {
        if (isViewAttached()) {
            view.showReviews(reviews);
        }
    }

    private void onGetReviewsFailure() {
        // Do nothing
    }

    @Override
    public void showFavoriteButton(Movie movie) {
        boolean isFavorite = favoritesInteractor.isFavorite(movie.getId());
        if (isViewAttached()) {
            if (isFavorite) {
                view.showFavorited();
            } else {
                view.showUnFavorited();
            }
        }
    }

    @Override
    public void onFavoriteClick(Movie movie) {
        if (isViewAttached()) {
            boolean isFavorite = favoritesInteractor.isFavorite(movie.getId());
            if (isFavorite) {
                favoritesInteractor.unFavorite(movie.getId());
                view.showUnFavorited();
            } else {
                System.out.println("favorite selected "+ movie.getId()+"\n");
                favoritesInteractor.setFavorite(movie);
                view.showFavorited();
            }
        }
    }

    @Override
    public void onWatchedClick(Movie movie) {
        Log.d("Todo", "onWatchedClick: Start from here");
                watchedInteractor.setWatched(movie);


    }
}
