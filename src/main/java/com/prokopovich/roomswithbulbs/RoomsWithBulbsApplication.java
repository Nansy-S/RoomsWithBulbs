package com.prokopovich.roomswithbulbs;

import com.prokopovich.roomswithbulbs.util.CreatedDatabase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RoomsWithBulbsApplication {

    public static void main(String[] args) {
        CreatedDatabase.createDatabase();
        SpringApplication.run(RoomsWithBulbsApplication.class, args);
    }
}
