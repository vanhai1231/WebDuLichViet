package com.hutech.DAMH.controller;

import com.hutech.DAMH.model.Tour;
import com.hutech.DAMH.service.ImagesService;
import com.hutech.DAMH.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/filter")
    public ResponseEntity<List<Tour>> filterTours(
            @RequestParam(required = false) String tenTour,
            @RequestParam(required = false) String noiKhoiHanh,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date ngayKH,
            @RequestParam(required = false) Integer giaTour) {

        List<Tour> tours = tourService.getFilteredTours(tenTour, noiKhoiHanh, ngayKH, giaTour);
        return ResponseEntity.ok(tours);
    }

}