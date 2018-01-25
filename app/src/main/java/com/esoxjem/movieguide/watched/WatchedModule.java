package com.esoxjem.movieguide.watched;

import com.esoxjem.movieguide.AppModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author pulkitkumar
 */
@Module(includes = AppModule.class)
public class WatchedModule
{
    @Provides
    @Singleton
    WatchedInteractor provideFavouritesInteractor(WatchedStore store)
    {
        return new WatchedInteractorImpl(store);
    }
}
