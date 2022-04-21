package ru.elias.weather.controller;

import java.time.Duration;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import ru.elias.weather.model.Weather;
import ru.elias.weather.service.WeatherService;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/cities")
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping(value = "/get-city/{id}")
    public Mono<Weather> findCityById(@PathVariable Integer id) {
        return weatherService.findById(id);
    }

    @GetMapping(value = "/get-city/hottest")
    public Mono<Weather> findHottestCity() {
        return weatherService.findHottestCity();
    }

    @GetMapping(value = "/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Weather> findAllCities() {
        Flux<Weather> data = weatherService.findAll();
        Flux<Long> delay = Flux.interval(Duration.ofSeconds(3));
        return Flux.zip(data, delay)
                   .map(Tuple2::getT1);
    }

    @GetMapping(value = "/all-great-than/{value}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Weather> findAllCitiesWithTemperatureGreatThan(@PathVariable Integer value) {
        Flux<Weather> data = weatherService.findAllGreatThanTemperature(value);
        Flux<Long> delay = Flux.interval(Duration.ofSeconds(3));
        return Flux.zip(data, delay)
                   .map(Tuple2::getT1);
    }

}
