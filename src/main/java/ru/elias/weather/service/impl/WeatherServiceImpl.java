package ru.elias.weather.service.impl;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.elias.weather.model.Weather;
import ru.elias.weather.repository.WeatherRepository;
import ru.elias.weather.service.WeatherService;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository weatherRepository;

    @Override
    public Mono<Weather> findById(Integer id) {
        return Mono.justOrEmpty(weatherRepository.findById(id));
    }

    @Override
    public Mono<Weather> findHottestCity() {
        return Mono.justOrEmpty(
                weatherRepository.findAll()
                                 .stream()
                                 .max(Comparator.comparing(Weather::getTemperature))
                                 .orElseThrow(NoSuchElementException::new)
        );
    }

    @Override
    public Flux<Weather> findAll() {
        return Flux.fromIterable(weatherRepository.findAll());
    }

    @Override
    public Flux<Weather> findAllGreatThanTemperature(Integer temperature) {
        return Flux.fromIterable(
                weatherRepository.findAll()
                                 .stream()
                                 .filter(city -> city.getTemperature() > temperature)
                                 .collect(Collectors.toList())
        );
    }

}
