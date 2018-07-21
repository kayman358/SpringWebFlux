/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springflux;

import com.spring.webflux.model.EventModel;
import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;
import org.springframework.boot.SpringApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

/**
 *
 * @author Ibrahim.Arowolo
 */
public class ReactiveServer {
    @GetMapping("/events/{id}")
    Mono<EventModel> eventById(@PathVariable long id){
       return Mono.just(new EventModel(id, new Date()));
    }
     @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/events")

    Flux<EventModel> events() {

        Flux<EventModel> eventFlux = Flux.fromStream(Stream.generate(() -> new EventModel(System.currentTimeMillis(), new Date())));

        Flux<Long> durationFlux = Flux.interval(Duration.ofSeconds(1));

        return Flux.zip(eventFlux, durationFlux).map(Tuple2::getT1);

    }

    public static void main(String[] args) {

        SpringApplication.run(ReactiveServer.class, args);

    }
    
}
