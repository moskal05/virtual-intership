package service;

import model.City;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface CityService {
    void readFile(File file);

    List<City> findAllCity();

    List<City> findSortedCity();

    List<City> findSortedCityByComparator();

    int[] maxPopulationCity();

    Map<String, Integer> countCityInRegion();
}
