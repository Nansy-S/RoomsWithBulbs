package com.prokopovich.roomswithbulbs.controller;

import com.prokopovich.roomswithbulbs.entity.Room;
import com.prokopovich.roomswithbulbs.enumeration.BulStatus;
import com.prokopovich.roomswithbulbs.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView index(Model model, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        String ipAddress = request.getRemoteAddr();
        //roomService.isCountryByIp(1, ipAddress);
        //roomService.isCountryByIp(1, request.getRemoteAddr());
        return modelAndView;
    }

    @GetMapping(value = {"/list"})
    public ModelAndView userList(Model model) {
        Room filterFieldRoom = new Room();

        List<Room> roomsList = roomService.getAllRooms();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("list");
        model.addAttribute("roomsList", roomsList);
        model.addAttribute("filterFieldRoom", filterFieldRoom);
        model.addAttribute("bulStatusList", BulStatus.getAllTitle());
        return modelAndView;
    }
}
