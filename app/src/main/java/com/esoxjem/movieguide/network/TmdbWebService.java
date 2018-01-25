package com.esoxjem.movieguide.network;

import com.esoxjem.movieguide.MoviesWraper;
import com.esoxjem.movieguide.ReviewsWrapper;
import com.esoxjem.movieguide.VideoWrapper;

import retrofit2.http.GET;
import retrofit2.http.Path;
import io.reactivex.Observable;
import retrofit2.http.Query;

/**
 * Created by ivan on 8/20/2017.
 */

public interface TmdbWebService {

//    String searchQuery="Inception";


    @GET("3/discover/movie?language=en&sort_by=popularity.desc")
    Observable<MoviesWraper> popularMovies();

    @GET("3/discover/movie?vote_count.gte=500&language=en&sort_by=vote_average.desc")
    Observable<MoviesWraper> highestRatedMovies();

    @GET("3/search/movie?language=en-US")
    Observable<MoviesWraper> search(@Query("query") String query);

//    @GET("3/search/movie?query=Inception")
//    Observable<MoviesWraper> search();

//    @GET("3/search/movie?query={movieName}")
//    Observable<MoviesWraper> search(@Path("id") int id);

    @GET("3/movie/{movieId}/videos")
    Observable<VideoWrapper> trailers(@Path("movieId") String movieId);

    @GET("3/movie/{movieId}/reviews")
    Observable<ReviewsWrapper> reviews(@Path("movieId") String movieId);

}
