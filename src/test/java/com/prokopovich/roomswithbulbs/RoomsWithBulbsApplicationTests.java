package com.prokopovich.roomswithbulbs;

import static org.assertj.core.api.Assertions.assertThat;

import com.prokopovich.roomswithbulbs.controller.RoomController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RoomsWithBulbsApplicationTests {

    @Autowired
    private RoomController controller;

    @Test
    void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
}
