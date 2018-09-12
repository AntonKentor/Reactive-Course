package com.reactive.netflux;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ReactiveStreamsExample {

    @Test
    public void simpleStreamExample() {
        Flux<String> dogs = Flux.just("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");
        dogs.toStream().forEach(System.out::println);
    }

    @Test
    public void simpleStreamExample2() {
        Flux<String> dogs = Flux.just("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");
        dogs.subscribe(System.out::println);
    }

    @Test
    public void simpleStreamExample3() {
        Flux<String> dogs = Flux.just("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");
        dogs.doOnEach(dog -> System.out.print(dog.get()));     //Doesnt do anything because it does not have a subscriber.
    }

    @Test
    public void simpleStreamExample4() {
        Flux<String> dogs = Flux.just("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");
        dogs.doOnEach(dog -> System.out.println(dog.get())).subscribe();    //Last element is null because there isnt any "when done function"
    }

    @Test
    public void simpleStreamExample5WithSubscriber() {
        Flux<String> dogs = Flux.just("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");
        dogs.subscribe(System.out::println, null, (() -> { System.out.println("Woo all done"); //Prints Woo all done when finished.
        }));
    }

    @Test
    public void simpleStreamExample5WithSubscriberConsumers() {

        //This example is the same as simpleStreamExample5WithSubscriber, but just in more peaces.

        Flux<String> dogs = Flux.just("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");

        //Subsriber lambda
        Consumer<String> println = System.out::println;

        //Error handler
        Consumer<Throwable> errorHandler = e -> System.out.println("Some error occured");

        //Runnable upon complete
        Runnable alldone = () -> System.out.println("Woo all done!");

        //Trigger subscription
        dogs.subscribe(println, errorHandler, alldone);

    }

    @Test
    public void mapStreamExample() {
        Flux<String> dogs = Flux.just("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");
        dogs.map(String::length)
                .doOnEach(System.out::println)
                .subscribe();
    }

    @Test
    public void filterStreamExample() {
        Flux<String> dogs = Flux.just("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");
        dogs.filter(s -> s.length() == 6).subscribe(System.out::println);
    }

    @Test
    public void filterAndLimitStreamExample() {
        Flux<String> dogs = Flux.just("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");
        dogs.filter(s -> s.length() == 6)
                .take(2)
                .subscribe(System.out::println);
    }

    @Test
    public void filterAndSortStreamExample() {
        Flux<String> dogs = Flux.just("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");
        dogs.filter(s -> s.length() == 6)
                .take(2)
                .sort()
                .subscribe(System.out::println);
    }

    @Test
    public void filterAndSortStreamWithCollectorExample() {
        Flux<String> dogs = Flux.just("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");
        dogs.filter(s -> s.length() == 6)
                .take(4)
                .sort()
                .collect(Collectors.joining(", "))
                .subscribe(System.out::println);
    }

    @Test
    public void testFlatMap() {
        Flux<List<List<Integer>>> listFlux = Flux.just(Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6)));
        listFlux
                .flatMap(Flux::fromIterable)
                .flatMap(Flux::fromIterable)
                .subscribe(System.out::println);
    }

    @Test
    public void testReduction() {
        Flux<String> dogs = Flux.just("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");
        dogs.reduce((a, b) -> a + " - " +b)
                .subscribe(System.out::println);
    }


}
