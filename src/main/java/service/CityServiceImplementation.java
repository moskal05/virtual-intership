package service;

import model.City;
import repository.CityRepository;
import util.UtilCityParse;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CityServiceImplementation implements CityService{
    private final CityRepository cityRepository;


    public CityServiceImplementation(CityRepository cityRepository){
        this.cityRepository = cityRepository;
    }
    @Override
    public void readFile(File file) {
        try(Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()){
                String[] arr = scanner.nextLine().split(";");
                cityRepository.create(UtilCityParse.arrayToCity(arr));
            }
        } catch (IOException e) {
            throw new IllegalArgumentException (e);
        }
    }

    @Override
    public List<City> findAllCity() {
        return cityRepository.findAll();
    }

    @Override
    public List<City> findSortedCity() {
        return null;
    }

    @Override
    public List<City> findSortedCityByComparator() {
        return null;
    }

    @Override
    public int[] maxPopulationCity() {
        return new int[0];
    }

    @Override
    public Map<String, Integer> countCityInRegion() {
        return null;
    }
}
