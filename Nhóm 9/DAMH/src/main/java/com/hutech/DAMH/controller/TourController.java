package com.hutech.DAMH.controller;

import com.hutech.DAMH.model.Tour;
import com.hutech.DAMH.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tours")
public class TourController {

    @Autowired
    private TourService tourService; // Inject your tour service

    @GetMapping("/filter")
    public List<Tour> filterTours(@RequestParam(required = false) String tourType,
                                  @RequestParam(required = false) String destination,
                                  @RequestParam(required = false) String departureDate,
                                  @RequestParam(required = false) Integer budget,
                                  @RequestParam(required = false) String transport,
                                  @RequestParam(required = false) Boolean promotion) {
        // Implement your logic to filter tours based on parameters
        List<Tour> filteredTours = tourService.filterTours(tourType, destination, departureDate, budget, transport, promotion);
        return filteredTours;
    }
}