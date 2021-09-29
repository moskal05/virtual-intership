package repository;

import model.City;

import java.util.List;

public interface CityRepository {
    void create (City city);
    List<City> findAll ();
}
