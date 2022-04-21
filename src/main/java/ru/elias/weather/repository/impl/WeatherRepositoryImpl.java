package ru.elias.weather.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;
import ru.elias.weather.model.Weather;
import ru.elias.weather.repository.WeatherRepository;

@Repository
public class WeatherRepositoryImpl implements WeatherRepository {

    private static final Map<Integer, Weather> weathers = new ConcurrentHashMap<>();

    static {
        weathers.put(1, new Weather(1, "Msc", 20));
        weathers.put(2, new Weather(2, "Saint-P", 19));
        weathers.put(3, new Weather(3, "Bryansk", 15));
        weathers.put(4, new Weather(4, "Lipetsk", 10));
        weathers.put(5, new Weather(5, "Kiev", 16));
        weathers.put(6, new Weather(6, "Minsk", 11));
    }

    @Override
    public Weather findById(Integer id) {
        return weathers.get(id);
    }

    @Override
    public List<Weather> findAll() {
        return new ArrayList<>(weathers.values());
    }

}
