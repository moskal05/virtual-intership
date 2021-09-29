package menu;

import model.City;
import service.CityService;
import service.CityServiceImplementation;

import java.util.HashMap;
import java.util.Scanner;

public class Menuimplementation implements Menu{

    private final CityService cityService;
    private final HashMap<Integer,String> menuNumber= new HashMap<>();
    /*      System.out.println("Выберете дальнейшее действие:");
        System.out.println("1) Список городов");
        System.out.println("2) Загрузить в базу данных");
        System.out.println("3) Отсортированный список городов");*/

    public Menuimplementation(CityService cityService){
        this.cityService = cityService;
        menuNumber.put(1, "Список городов");
        menuNumber.put(2, "Отсортированный список городов");
    }

    @Override
    public void createMenu() {
        int numberCommand;
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Выберете дальнейшее действие:");
            for(Integer currentNum: menuNumber.keySet()){
                System.out.println(currentNum + ") "+ menuNumber.get(currentNum));
            }
            numberCommand = scanner.nextInt();
            switch (numberCommand){
                case(1):
                    for (City city: cityService.findAllCity()){
                        System.out.println(city);
                    }
                    break;
            }
        }
    }
}
