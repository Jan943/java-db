package pl.sopata.jdbccar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CarDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CarDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Car car){

        String sql = "INSERT INTO Car VALUES(?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{
           car.getCarId(),
           car.getMark(),
           car.getModel(),
           car.getColor()
        });

    }

    @EventListener(ApplicationReadyEvent.class)
    public void init(){
        save(new Car(1,"Fiat","126p","red"));
        save(new Car(2,"Fiat","125p","black"));
        save(new Car(3,"Audi","A1","silver"));
        save(new Car(4,"Audi","A2","white"));
    }
}
