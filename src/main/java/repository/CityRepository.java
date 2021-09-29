package repository;

import model.City;

import java.util.List;
import java.util.Optional;

public interface CityRepository {
    void create (City city);
    List<City> findAll ();
}
