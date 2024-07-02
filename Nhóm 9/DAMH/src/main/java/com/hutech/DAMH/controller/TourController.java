package com.hutech.DAMH.controller;

import com.hutech.DAMH.model.Tour;
import com.hutech.DAMH.service.ImagesService;
import com.hutech.DAMH.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class TourController {
    @Autowired
    private TourService tourService;

    @Autowired
    private ImagesService imagesService;

        @GetMapping("/applyPromotion")
        public ResponseEntity<?> applyPromotion(@RequestParam("maTour") String maTour, @RequestParam("maKM") String maKM) {
            try {
                Map<String, Object> promotionInfo = tourService.applyPromotionAndGetNewPrice(maTour, maKM);
                return ResponseEntity.ok(promotionInfo);
            } catch (IllegalArgumentException e) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", e.getMessage());
                return ResponseEntity.badRequest().body(errorResponse);
            } catch (SecurityException e) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", e.getMessage());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
            }
        }
    @PostMapping("/filter")
    public ResponseEntity<List<Tour>> filterTours(
            @RequestParam(required = false) String tourType,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date departureDate,
            @RequestParam(required = false) Integer budget,
            @RequestParam(required = false) String transport,
            @RequestParam(required = false) Boolean promotion
    ) {
        try {
            List<Tour> filteredTours = tourService.getFilteredTours(tourType, location, departureDate, budget, transport, promotion);
            return ResponseEntity.ok(filteredTours);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}