package util;

import model.City;

public class UtilCityParse {
    public static City arrayToCity(String[] arr){
        return City.builder()
                .name(arr[1])
                .region(arr[2])
                .district(arr[3])
                .population(Integer.parseInt(arr[4]))
                .foundation(arr[5])
                .build();
    }
}
