package pl.sopata.springdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

public class Start {

    private CarRepo carRepo;

    @Autowired
    public Start(CarRepo carRepo) {
        this.carRepo = carRepo;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runExample() {
        Car car = new Car("Honda", "Jazz", Color.BLUE);
        carRepo.save(car);

        Car car2 = new Car("Audi", "A1", Color.BLACK);
        carRepo.save(car2);

        Car car3 = new Car("Audi", "A2", Color.RED);
        carRepo.save(car3);

        carRepo.deleteById(1L);

        Iterable<Car> allByColor = carRepo.findAllByColor(Color.RED);
        allByColor.forEach(System.out::println);

        Iterable<Car> all = carRepo.findAll();
        all.forEach(System.out::println);
    }
}
