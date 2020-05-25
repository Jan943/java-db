package pl.sopata.jdbccar;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class Config {

    @Bean
    public DataSource getDataSource(){

        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:mysql://mysql.agh.edu.pl:3306/jsopata?serverTimezone=UTC");
        dataSourceBuilder.username("jsopata");
        dataSourceBuilder.password("E3ZRJv6ZAz8n4TKP");
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        return dataSourceBuilder.build();
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(){

        return new JdbcTemplate(getDataSource());
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init(){

        String sql = "CREATE TABLE Car(car_id int, mark varchar(255), model varchar(255), color varchar(255));";
        getJdbcTemplate().update(sql);

    }
}
