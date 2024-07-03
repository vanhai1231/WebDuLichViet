package com.hutech.DAMH.service;

import com.hutech.DAMH.model.Destination;
import com.hutech.DAMH.model.LoaiPhong;
import com.hutech.DAMH.repository.DestinationRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

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
    public String uploadImage(MultipartFile imageFile) throws IOException {
        if (imageFile.isEmpty()) {
            throw new IllegalArgumentException("Image file is empty");
        }

        // Ensure the upload directory exists; create if it doesn't
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath); // Create directories if they don't exist
        }

        // Generate a unique file name to prevent overwriting existing files
        String originalFileName = imageFile.getOriginalFilename();
        String uniqueFileName = System.currentTimeMillis() + "_" + originalFileName;

        // Save the file to the upload directory
        Path filePath = uploadPath.resolve(uniqueFileName);
        Files.copy(imageFile.getInputStream(), filePath);

        // Return the file path (relative to the project)
        return "/images/" + uniqueFileName; // Trả về đường dẫn cắt bớt chỉ lưu "img/"
    }
    private static final String UPLOAD_DIR = "C:/Users/Admin/Source/Nhóm 9/DAMH/src/main/resources/static/images/";
    public Destination addDD(Destination destination) {
        return destinationRepository.save(destination);
    }
    public Optional<Destination> getId(int id) {
        return destinationRepository.findById(id);
    }
    public Destination updateDD(@NotNull Destination destination) {
        Destination existingDiemDen = destinationRepository.findById(destination.getId())
                .orElseThrow(() -> new IllegalStateException("DiemDen with ID " +
                        destination.getId() + " does not exist."));

        existingDiemDen.setTenDiemDen(destination.getTenDiemDen());
//        existingDiemDen.setHinhAnh(String.valueOf(destination.getHinhAnhFile()));
        existingDiemDen.setMoTa(destination.getMoTa());
        existingDiemDen.setMaTinh(destination.getMaTinh());

        return destinationRepository.save(existingDiemDen);
    }
    public void deleteDDById(int id) {
        if (!destinationRepository.existsById(id)) {
            throw new IllegalStateException("DiemDen with ID " + id + " does not exist.");
        }
        destinationRepository.deleteById(id);
    }
}
