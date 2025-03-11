package ro.mpp;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class MainBD {
    public static void main(String[] args) {

        Properties props=new Properties();
        try {
            props.load(new FileReader("C:\\Users\\maria\\Desktop\\Year2\\Semestru 2\\MPP\\Laborator\\lab03database\\bd.config"));
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

    }
}
