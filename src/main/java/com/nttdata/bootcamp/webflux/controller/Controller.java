package com.nttdata.bootcamp.webflux.controller;

import java.time.Duration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class Controller {

	@GetMapping(path="/numbers", produces="text/event-stream")
	public Flux<Integer> numbers(){
		
		Flux<Integer> messageSender = Flux.range(1, 30).delayElements(Duration.ofSeconds(1));
		
		return messageSender;
	}
	
	@GetMapping(path="/numbeers-with-subscribers", produces="text/event-stream")
	public Flux<Integer> numbersWithSubscribers(){
		
		Flux<Integer> messageSender = Flux.range(1, 30).delayElements(Duration.ofSeconds(1));
		
		messageSender.subscribe(n -> Subscriber.print(n));
		messageSender.subscribe(n -> System.out.println("Subscriptor 2: "+ n));
		
		return messageSender;
	}
}
