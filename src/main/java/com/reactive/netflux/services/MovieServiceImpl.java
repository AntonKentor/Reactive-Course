package com.reactive.netflux.services;

import com.reactive.netflux.domain.Movie;
import com.reactive.netflux.domain.MovieEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MovieServiceImpl implements MovieService {

    @Override
    public Flux<MovieEvent> events(String movieId) {
        return null;
    }

    @Override
    public Mono<Movie> getMoviebyId(String id) {
        return null;
    }

    @Override
    public Flux<Movie> getAllMovies() {
        return null;
    }
}
