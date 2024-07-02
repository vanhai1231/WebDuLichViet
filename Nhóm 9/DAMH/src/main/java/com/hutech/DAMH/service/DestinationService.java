package com.hutech.DAMH.service;

import com.hutech.DAMH.model.Destination;
import com.hutech.DAMH.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationService {

    @Autowired
    private DestinationRepository destinationRepository;

    public List<Destination> getAllDestinations() {
        return destinationRepository.findAll();
    }

    public List<Destination> getDestinationsByProvince(String maTinh) {
        return destinationRepository.findByMaTinh_MaTinh(maTinh);
    }
}
