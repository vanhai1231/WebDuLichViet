package com.hutech.DAMH.controller;

import com.hutech.DAMH.model.Tinh;
import com.hutech.DAMH.service.ThanhPhoService;
import com.hutech.DAMH.service.TinhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SuggestionsController {

    @Autowired
    private TinhService tinhService;
    @Autowired
    private ThanhPhoService thanhPhoService;

    @GetMapping("/suggestions")
    public ResponseEntity<Map<String, List<?>>> getSearchSuggestions(@RequestParam String query) {
        Map<String, List<?>> suggestions = new HashMap<>();
        suggestions.put("tinhSuggestions", tinhService.getSuggestions(query));
        suggestions.put("thanhPhoSuggestions", thanhPhoService.getSuggestions(query));
        return ResponseEntity.ok(suggestions);

    }
}
