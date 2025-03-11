package ro.mpp;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class MainBD {
    public static void main(String[] args) {

        Properties props=new Properties();
        try {
            props.load(new FileReader("bd.config"));
        } catch (IOException e) {
            System.out.println("Cannot find bd.config "+e);
        }

        CarRepository carRepo=new CarsDBRepository(props);

        carRepo.add(new Car("Tesla","Model S", 2019));
        System.out.println("Toate masinile din db");
        for(Car car:carRepo.findAll())
            System.out.println(car);

       String manufacturer="Tesla";
       System.out.println("Masinile produse de "+manufacturer);
       for(Car car:carRepo.findByManufacturer(manufacturer))
           System.out.println(car);

       String minYear = "2010";
       String maxYear = "2020";
       System.out.println("Masinile produse intre anii " + minYear + " " + maxYear);
       for (Car car:carRepo.findBetweenYears(Integer.parseInt(minYear), Integer.parseInt(maxYear)))
           System.out.println(car);


       Car updated = new Car("MASINA UPDATED", "MODEL", 2000);
       System.out.println("Se actualizeaza masina cu id 13");
       carRepo.update(13, updated);

        System.out.println("Toate masinile din db");
        for(Car car:carRepo.findAll())
            System.out.println(car);

    }
}
