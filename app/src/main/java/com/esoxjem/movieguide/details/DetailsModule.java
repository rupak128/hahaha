package com.esoxjem.movieguide.details;

import com.esoxjem.movieguide.favorites.FavoritesInteractor;
import com.esoxjem.movieguide.network.TmdbWebService;

import dagger.Module;
import dagger.Provides;

@Module
public class DetailsModule {
    @Provides
    @DetailsScope
    MovieDetailsInteractor provideInteractor(TmdbWebService tmdbWebService) {
        return new MovieDetailsInteractorImpl(tmdbWebService);
    }

    @Provides
    @DetailsScope
    MovieDetailsPresenter providePresenter(MovieDetailsInteractor detailsInteractor,
                                           FavoritesInteractor favoritesInteractor) {
        return new MovieDetailsPresenterImpl(detailsInteractor, favoritesInteractor);
    }
}
