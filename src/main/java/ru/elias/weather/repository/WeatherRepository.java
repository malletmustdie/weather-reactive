package ru.elias.weather.repository;

import java.util.List;

import ru.elias.weather.model.Weather;

public interface WeatherRepository {

    Weather findById(Integer id);

    List<Weather> findAll();

}
