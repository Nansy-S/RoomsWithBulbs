package com.prokopovich.roomswithbulbs.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.prokopovich.roomswithbulbs.entity.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = "/create-test-database-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/create-test-database-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class RoomControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RoomController controller;

    @Test
    public void displayRoomsListTest() throws Exception {
        String[] roomsLinks = {"/", "/index", "/room"};
        for(String roomsLink : roomsLinks) {
            this.mockMvc.perform(get(roomsLink))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(xpath("//div[@id='home']/div").nodeCount(3));
        }
    }

    @Test
    public void filterRoomsListTest() throws Exception {
        Room filterFieldRoomByCountry = new Room(0, "", "Belarus", "");
        this.mockMvc.perform(post("/room").flashAttr("filterFieldRoom", filterFieldRoomByCountry))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("//div[@id='home']/div").nodeCount(1));
        Room filterFieldRoomByBulbStatus = new Room(0, "", "", "OFF");
        this.mockMvc.perform(post("/room").flashAttr("filterFieldRoom", filterFieldRoomByBulbStatus))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("//div[@id='home']/div").nodeCount(2));
        Room filterFieldRoomClean = new Room(0, "", "", "");
        this.mockMvc.perform(post("/room").flashAttr("filterFieldRoom", filterFieldRoomClean))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("//div[@id='home']/div").nodeCount(3));
        Room filterFieldRoom = new Room(0, "My Room", "", "ON");
        this.mockMvc.perform(post("/room").flashAttr("filterFieldRoom", filterFieldRoom))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("//div[@id='notFound']/h2").string("Rooms not found."));
    }

    @Test
    public void roomDetailTest() throws Exception {
        RequestPostProcessor request = new RequestPostProcessor() {
            public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                request.setRemoteAddr("185.70.12.0");
                return request;
            }
        };
        this.mockMvc.perform(get("/room/{id}","1")
                .with(request))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("//div[@class='roomDetail']/p").string("Name: Test Room 1"));
        this.mockMvc.perform(get("/room/{id}","2")
                .with(request))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/403"));
    }

    @Test
    public void changeStatusTest() throws Exception {
        RequestPostProcessor request = new RequestPostProcessor() {
            public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                request.setRemoteAddr("185.70.12.0");
                return request;
            }
        };
        this.mockMvc.perform(post("/room/{id}","1").param("newStatus", "ON"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/room/1"));
    }

    @Test
    public void addRoomPageTest() throws Exception {
        this.mockMvc.perform(get("/room/new"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("//h2").string("Enter information"));
    }

    @Test
    public void saveUserTest() throws Exception {
        Room newRoom = new Room(4, "Test Room 4", "Belarus", "OFF");
        this.mockMvc.perform(post("/room/new").flashAttr("newRoom", newRoom))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("//div[@id='home']/div").nodeCount(4));
    }
}