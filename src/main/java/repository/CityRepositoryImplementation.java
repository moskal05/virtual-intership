package repository;

import model.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CityRepositoryImplementation implements CityRepository {
    private final String DB_URL;

    //language=SQL
    private final String CREATE_TABLE = "create table if not exists CITY_DB(id int not null auto_increment," +
            " cityname varchar (100), region varchar (100), district varchar (100)," +
            " population int, foundation varchar (100)\n)";

    //language=SQL
    private final String SELECT_CITY = "select id from CITY_DB where cityname=? and region=?";

    //language=SQL
    private final String INSERT_CITY = "insert into CITY_DB (cityname, region, district, population, foundation) " +
            "values (?,?,?,?,?)";

    //language=SQL
    private final String FIND_ALL_CITY = "select * from CITY_DB";

    Function<ResultSet, City> rowMapper = resultSet -> {
        City city;
        try{
            city = City.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("cityname"))
                    .region(resultSet.getString("region"))
                    .district(resultSet.getString("district"))
                    .population(resultSet.getInt("population"))
                    .foundation(resultSet.getString("foundation"))
                    .build();
        } catch (SQLException e){
            throw new IllegalArgumentException(e);
        }
        return city;
    };

    public CityRepositoryImplementation(String DB_URL) {
        this.DB_URL = DB_URL;
        try (PreparedStatement statement = DriverManager.getConnection(DB_URL).prepareStatement(CREATE_TABLE) ){
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
    @Override
    public void create(City city) {
        try(Connection connection = DriverManager.getConnection(DB_URL);
            PreparedStatement statement = connection.prepareStatement(SELECT_CITY)) {
            statement.setString(1, city.getName());
            statement.setString(2, city.getRegion());
            try (ResultSet resultSet = statement.executeQuery();) {
                if (!resultSet.next()) {
                    try (PreparedStatement statement2 = connection.prepareStatement(INSERT_CITY, statement.RETURN_GENERATED_KEYS)){
                        statement2.setString(1, city.getName());
                        statement2.setString(2, city.getRegion());
                        statement2.setString(3, city.getDistrict());
                        statement2.setInt(4, city.getPopulation());
                        statement2.setString(5, city.getFoundation());
                        int id = statement2.executeUpdate();
                        city.setId(id);
                    }
                }
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public List<City> findAll() {
        List<City> cities = new ArrayList();
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_CITY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next())
                cities.add(rowMapper.apply(resultSet));
            return cities;
        } catch (SQLException throwable) {
            throw new IllegalArgumentException(throwable);
        }
    }
}
