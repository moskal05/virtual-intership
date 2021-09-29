package service;

import model.City;
import repository.CityRepository;
import util.UtilCityParse;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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

    /**
     * Сортировка списка городов по наименованию в алфавитном порядке по убыванию без учета регистра;
     * @return
     */
    @Override
    public List<City> findSortedCity() {
        return cityRepository.findAll().stream()
                .sorted(Comparator.comparing(City::getName))
                .collect(Collectors.toList());
    }

    /**
     * Сортировка списка городов по федеральному округу и наименованию
     * города внутри каждого федерального округа в алфавитном порядке по убыванию с учетом регистра
     * @return
     */
    @Override
    public List<City> findSortedCityByComparator() {
            return cityRepository.findAll().stream()
                    .sorted(Comparator.comparing(City::getRegion).thenComparing(City::getName))
                    .collect(Collectors.toList());
    }

    /**
     * Поиск города с наибольшим количеством жителей
     * @return
     */
    @Override
    public int[] maxPopulationCity() {
        int maxIndexPopulation = 0;
        int maxCountPopulation = 0;
        for(int i = 0; cityRepository.findAll().size() > i; i++){
            int currentPopulation = cityRepository.findAll().get(i).getPopulation();
            if(currentPopulation > maxCountPopulation){
                maxCountPopulation = currentPopulation;
                maxIndexPopulation = i;
            }
        }
        return new int [] {maxIndexPopulation, maxCountPopulation};
    }

    /**
     * Поиск количества городов в разрезе регионов
     * @return
     */
    @Override
    public Map<String, Integer> countCityInRegion() {
        Map<String, Integer> countCityInRegion = new HashMap<String, Integer>();
        List<City> cities = cityRepository.findAll();
        for(int i = 0; cities.size() > i; i++ ){
            if(countCityInRegion.containsKey(cities.get(i).getRegion()))
                countCityInRegion.put(cities.get(i).getRegion(),countCityInRegion.get(cities.get(i).getRegion()) + 1);
            else
                countCityInRegion.put(cities.get(i).getRegion(),1);
        }
        return countCityInRegion;
    }
}
