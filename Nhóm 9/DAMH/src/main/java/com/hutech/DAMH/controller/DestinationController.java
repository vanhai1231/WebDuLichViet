package com.hutech.DAMH.controller;

import com.hutech.DAMH.model.Destination;
import com.hutech.DAMH.model.Tinh;
import com.hutech.DAMH.service.DestinationService;
import com.hutech.DAMH.service.TinhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/DuLichViet")
public class DestinationController {
    @Autowired
    private DestinationService destinationService;

    @Autowired
    private TinhService tinhService;

    @GetMapping("/Destination")
    public String viewDestination(@RequestParam(value = "province", required = false) String provinceId, Model model) {
        // Kiểm tra nếu provinceId không null và không trống thì lọc theo tỉnh
        if (provinceId != null && !provinceId.isEmpty()) {
            List<Destination> destinations = destinationService.getDestinationsByProvince(provinceId);
            model.addAttribute("attractions", destinations);
        } else {
            // Nếu không có provinceId, lấy tất cả các điểm đến
            List<Destination> destinations = destinationService.getAllDestinations();
            model.addAttribute("attractions", destinations);
        }

        // Lấy danh sách tỉnh để hiển thị trong dropdown lọc
        List<Tinh> tinhList = tinhService.getAllTinh();
        model.addAttribute("tinhList", tinhList);

        // Trả về tên view template (thường là tên của file HTML Thymeleaf)
        return "index/Destination";
    }
    @PostMapping("/FilterDestination")
    @ResponseBody // Trả về JSON
    public ResponseEntity<Map<String, Object>> viewDestinationPost(@RequestParam(value = "province", required = false) String provinceId) {
        // Lấy danh sách điểm đến theo provinceId
        List<Destination> destinations = destinationService.getDestinationsByProvince(provinceId);

        // Lấy danh sách tỉnh
        List<Tinh> tinhList = tinhService.getAllTinh();

        // Tạo response JSON
        Map<String, Object> response = new HashMap<>();
        response.put("attractions", destinations);
        response.put("tinhList", tinhList);

        return ResponseEntity.ok(response);
    }
}
