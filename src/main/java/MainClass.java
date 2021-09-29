import menu.Menu;
import menu.Menuimplementation;
import repository.CityRepository;
import repository.CityRepositoryImplementation;
import service.CityService;
import service.CityServiceImplementation;

import java.io.*;

public class MainClass {

    public static void main(String[] args) {
        final String DB_URL = "jdbc:h2:/Users/u19571300/Ide1aProjects/virtual-internship/db/stockExchange";
        final String DB_Driver = "org.h2.Driver";
        try {
            Class.forName(DB_Driver); //Проверяем наличие JDBC драйвера для работы с БД
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
        CityRepository cityRepository = new CityRepositoryImplementation(DB_URL);
        CityService cityService = new CityServiceImplementation(cityRepository);
        File file = new File("/Users/u19571300/IdeaProjects/virtual-internship/src/main/resources/city.txt");
        cityService.readFile(file);
        Menu menu = new Menuimplementation(cityService);
        menu.createMenu();
    }

}
