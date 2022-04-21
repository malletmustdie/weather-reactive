package ru.elias.weather.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.elias.weather.model.Weather;

public interface WeatherService {

    Mono<Weather> findById(Integer id);

    Mono<Weather> findHottestCity();

    Flux<Weather> findAll();

    Flux<Weather> findAllGreatThanTemperature(Integer temperature);

}
