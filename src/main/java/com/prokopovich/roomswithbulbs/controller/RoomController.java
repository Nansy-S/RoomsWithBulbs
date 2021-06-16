package com.prokopovich.roomswithbulbs.controller;

import com.prokopovich.roomswithbulbs.entity.Room;
import com.prokopovich.roomswithbulbs.enumeration.BulbStatus;
import com.prokopovich.roomswithbulbs.service.CountryService;
import com.prokopovich.roomswithbulbs.service.RoomService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping
public class RoomController {

    private static final Logger LOGGER = LogManager.getLogger(RoomController.class);

    private final RoomService roomService;
    private final CountryService countryService;

    @Autowired
    public RoomController(RoomService roomService, CountryService countryService) {
        this.roomService = roomService;
        this.countryService = countryService;
    }

    //@GetMapping(value = {"/", "/index"})
    //public ModelAndView index(Model model, HttpServletRequest request) {
    //    List<Room> roomsList = roomService.getAllRooms();
    //    ModelAndView modelAndView = new ModelAndView();
    //    modelAndView.setViewName("index");
    //    displayRooms(model, roomsList);
//
   ////     String ipAddress = request.getRemoteAddr();
    //    countryService.isCountryByIp(1, "188.130.170.0");
    //    //roomService.isCountryByIp(1, request.getRemoteAddr());
    //    LOGGER.info("/index - GET was called");
    //    return modelAndView;
    //}

    @GetMapping(value = {"/", "/index", "/room", })
    public ModelAndView displayRoomsList(Model model) {
        List<Room> roomsList = roomService.getAllRooms();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("roomsList");
        displayRooms(model, roomsList);
        LOGGER.info("/room/list - GET was called");
        return modelAndView;
    }

    @PostMapping(value = {"/room"})
    public ModelAndView filterRoomsList(Model model,
                                       @ModelAttribute("filterFieldRoom") Room filterFieldRoom) {
        List<Room> roomsList = roomService.filterRooms(
                filterFieldRoom.getName(),
                filterFieldRoom.getCountry(),
                filterFieldRoom.getBulbStatus());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("roomsList");
        displayRooms(model, roomsList);
        LOGGER.info("/user was called");
        return modelAndView;
    }

    private void displayRooms(Model model, List<Room> roomsList) {
        Room filterFieldRoom = new Room();
        model.addAttribute("roomsList", roomsList);
        model.addAttribute("filterFieldRoom", filterFieldRoom);
        model.addAttribute("bulbStatusList", BulbStatus.getAllTitle());
        model.addAttribute("countryNameList", countryService.getAllCountryName());
    }

    @GetMapping(value = {"/room/{id}"})
    public ModelAndView roomDetail(@PathVariable("id") int id) {
        Room room = roomService.getRoomById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("roomDetail");
        modelAndView.addObject("room", room);
        LOGGER.info("roomDetail - GET was called");
        return modelAndView;
    }

    @GetMapping(value = "/room/new")
    public ModelAndView addRoomPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("addRoom");
        Room roomForm = new Room();
        model.addAttribute("roomForm", roomForm);
        model.addAttribute("countryNameList", countryService.getAllCountryName());
        LOGGER.info("addRoom - GET was called");
        return modelAndView;
    }

    @PostMapping(value = "/room/new")
    public ModelAndView saveUser(Model model, @ModelAttribute("roomForm") Room newRoom) {
        LOGGER.info("/room/new - POST was called");
        ModelAndView modelAndView = new ModelAndView();
        newRoom = roomService.addNewRoom(newRoom);
        modelAndView.setViewName("redirect:/room/" + newRoom.getId());
        return modelAndView;
    }
}
