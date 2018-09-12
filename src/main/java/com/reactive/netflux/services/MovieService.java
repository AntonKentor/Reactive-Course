package com.reactive.netflux.services;

import com.reactive.netflux.domain.Movie;
import com.reactive.netflux.domain.MovieEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieService {

    Flux<MovieEvent> events(String movieId);

    Mono<Movie> getMoviebyId(String id);

    Flux<Movie> getAllMovies();


}
