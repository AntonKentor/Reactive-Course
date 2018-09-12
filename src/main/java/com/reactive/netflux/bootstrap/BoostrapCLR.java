package com.reactive.netflux.bootstrap;

import com.reactive.netflux.domain.Movie;
import com.reactive.netflux.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import reactor.core.publisher.Flux;

import java.util.UUID;

public class BoostrapCLR implements CommandLineRunner {

    private final MovieRepository movieRepository;

    @Autowired
    public BoostrapCLR(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        //clear old data
        movieRepository.deleteAll().block();

        Flux.just("Silence of the Lambdas", "AEon Flux", "Enter the Mono<Void>", "The Fluxxinator", "Back to the future", "Meet the Fluxes", "Lord of the Fluxes")
                .map(title -> new Movie(title, UUID.randomUUID().toString()))
                .flatMap(movieRepository::save)
                .subscribe(null, null, () -> {
                    movieRepository.findAll().subscribe(System.out::println);
                });
    }
}