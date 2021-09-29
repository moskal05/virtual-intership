package menu;

import model.City;
import service.CityService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Menuimplementation implements Menu{
    private final CityService cityService;
    private final HashMap<Integer,String> menuNumber= new HashMap<>();

    public Menuimplementation(CityService cityService){
        this.cityService = cityService;
        menuNumber.put(1, "Список городов");
        menuNumber.put(2, "Отсортированный список городов");
        menuNumber.put(3, "Отсортированный список городов по федеральному округу и наименованию города");
        menuNumber.put(4, "Поиск города с наибольшим количеством жителей");
        menuNumber.put(5, "Поиск количества городов в разрезе регионов");
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
            switch (numberCommand) {
                case (1):
                    for (City city : cityService.findAllCity()) {
                        System.out.println(city);
                    }
                    break;
                case (2):
                    for (City city : cityService.findSortedCity()) {
                        System.out.println(city);
                    }
                    break;
                case (3):
                    for (City city : cityService.findSortedCityByComparator()) {
                        System.out.println(city);
                    }
                    break;
                case (4):
                    int[] maxPopulationCity = cityService.maxPopulationCity();
                    System.out.print(maxPopulationCity[0]);
                    System.out.print(" = ");
                    System.out.println(maxPopulationCity[1]);
                    break;
                case (5):
                    Map<String, Integer> test = cityService.countCityInRegion();
                    Set<String> test1 = cityService.countCityInRegion().keySet();
                    for (String region : cityService.countCityInRegion().keySet()){
                        System.out.println(region + " - " + cityService.countCityInRegion().get(region));
                    }
                    break;
                case (0):
                    System.exit(0);
                default:
                    System.out.println("Команда неверная");
            }
        }
    }
}
