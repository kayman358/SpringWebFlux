/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springflux;

import com.spring.webflux.model.EventModel;
import java.util.Collections;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

/**
 *
 * @author Ibrahim.Arowolo
 */
@SpringBootApplication
public class ReactiveClient {
    @Bean

    WebClient client() {

        return WebClient.create("http://localhost:8070");

    }
    @Bean

    CommandLineRunner demo(WebClient client) {

      return args -> {client.get().uri("/events").accept(MediaType.TEXT_EVENT_STREAM).exchange().flatMapMany(cr -> cr.bodyToFlux(EventModel.class)).subscribe(System.out::println);};

    }

    public static void main(String[] args) {

        new SpringApplicationBuilder(ReactiveClient.class).properties(Collections.singletonMap("server.port", "8081")).run(args);

    }
    
}
