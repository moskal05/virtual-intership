package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class City {
    private int id;
    private String name;
    private String region;
    private String district;
    private int population;
    private String foundation;


}
