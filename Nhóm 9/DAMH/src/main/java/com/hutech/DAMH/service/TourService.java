package com.hutech.DAMH.service;

import com.hutech.DAMH.CustomUserDetails;
import com.hutech.DAMH.model.*;
import com.hutech.DAMH.repository.HinhAnhRepository;
import com.hutech.DAMH.repository.ImagesRepository;
import com.hutech.DAMH.repository.KhuyenMaiRespository;
import com.hutech.DAMH.repository.TourRepository;
import com.hutech.DAMH.specification.TourSpecification;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TourService {

    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private ImagesRepository imagesRepository;
    @Autowired
    private KhuyenMaiRespository khuyenMaiRespository;
    @Autowired
    private HinhAnhRepository hinhAnhRepository;
    public List<Images> getImagesByMaTour(String maTour) {
        return imagesRepository.findByMaTour(maTour);
    }
    public List<Tour> getAllTours() {
        List<Tour> tours = tourRepository.findAll();

        for (Tour tour : tours) {
            List<Images> images = imagesRepository.findByMaTour(tour.getMaTour());
            List<String> imageUrls = images.stream().map(image -> image.getHinhAnh().getImg()).toList();

            if (!imageUrls.isEmpty()) {
                tour.setMainImageUrl(imageUrls.getFirst());

            }
        }
        return tours;
    }

    public Page<Tour> getProductsbyPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return tourRepository.findAll(pageable);
    }

    public List<Tour> getFilteredTours(String tourType, String location, Date departureDate, Integer budget, String transport, Boolean promotion) {
        Specification<Tour> spec = Specification.where(null);

        if (tourType != null && !tourType.isEmpty()) {
            spec = spec.and(TourSpecification.withField("loaiTour.maLoaiTour", tourType));
        }
        if (location != null && !location.isEmpty()) {
            spec = spec.and(TourSpecification.withField("noiKhoiHanh", location));
        }
        if (departureDate != null) {
            spec = spec.and(TourSpecification.withField("ngayKH", departureDate));
        }
        if (budget != null) {
            spec = spec.and(TourSpecification.withField("giaTour", budget));
        }
        if (transport != null && !transport.isEmpty()) {
            spec = spec.and(TourSpecification.withField("phuongTien.maPhuongTien", transport));
        }
        if (promotion != null && promotion) {
            spec = spec.and(TourSpecification.withField("promotionActive", true));
        }

        return tourRepository.findAll(spec);
    }



    public Tour getTourByMaTour(String maTour) {
        return tourRepository.findByMaTour(maTour);
    }

    public Tour addTour(Tour tour, String mainImageUrl, String secondaryImageUrl) {
        // Save the tour entity
        Tour savedTour = tourRepository.save(tour);

        // Create Images entity for the main image URL and associate it with the saved tour
        Images mainImage = new Images();
        mainImage.setMaTour(savedTour.getMaTour());
        HinhAnh mainHinhAnh = new HinhAnh();
        mainHinhAnh.setImg(mainImageUrl);
        hinhAnhRepository.save(mainHinhAnh);
        mainImage.setId(mainHinhAnh.getId());
        imagesRepository.save(mainImage);

        // Create Images entity for the secondary image URL and associate it with the saved tour
        Images secondaryImage = new Images();
        secondaryImage.setMaTour(savedTour.getMaTour());
        HinhAnh secondaryHinhAnh = new HinhAnh();
        secondaryHinhAnh.setImg(secondaryImageUrl);
        hinhAnhRepository.save(secondaryHinhAnh);
        secondaryImage.setId(secondaryHinhAnh.getId());
        imagesRepository.save(secondaryImage);

        return savedTour;
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

    @Transactional(rollbackFor = Exception.class)
    public Tour updateTour(Tour updatedTour, MultipartFile mainImageFile, MultipartFile secondaryImageFile) throws IOException {
        Tour existingTour = tourRepository.findById(updatedTour.getMaTour())
                .orElseThrow(() -> new IllegalStateException("Tour with ID " + updatedTour.getMaTour() + " does not exist."));

        // Update tour details
        existingTour.setGiaTour(updatedTour.getGiaTour());
        existingTour.setLoaiTour(updatedTour.getLoaiTour());
        existingTour.setTenTour(updatedTour.getTenTour());
        existingTour.setNgayKH(updatedTour.getNgayKH());
        existingTour.setPhong(updatedTour.getPhong());
        existingTour.setNoiKhoiHanh(updatedTour.getNoiKhoiHanh());
        existingTour.setTinh(updatedTour.getTinh());
        existingTour.setSoluong(updatedTour.getSoluong());
        existingTour.setPhuongTien(updatedTour.getPhuongTien());

        // Update main image if provided
        if (mainImageFile != null && !mainImageFile.isEmpty()) {
            String mainImageUrl = uploadImage(mainImageFile);
            updateMainImageForTour(existingTour, mainImageUrl);
        }

        // Update secondary image if provided
        if (secondaryImageFile != null && !secondaryImageFile.isEmpty()) {
            String secondaryImageUrl = uploadImage(secondaryImageFile);
            updateSecondaryImageForTour(existingTour, secondaryImageUrl);
        }

        return tourRepository.save(existingTour);
    }

    public void updateMainImageForTour(Tour tour, String mainImageUrl) {
        List<Images> images = imagesRepository.findByMaTour(tour.getMaTour());
        if (!images.isEmpty()) {
            Images image = images.get(0);
            HinhAnh hinhAnh = hinhAnhRepository.findById(image.getId()).orElse(null);
            if (hinhAnh != null) {
                hinhAnh.setImg(mainImageUrl);
                hinhAnhRepository.save(hinhAnh);
            }
        } else {
            addMainImageToTour(tour, mainImageUrl);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void addSecondaryImageToTour(Tour tour, String secondaryImageUrl) {
        // Create Images entity for the secondary image URL and associate it with the tour
        Images secondaryImage = new Images();
        secondaryImage.setMaTour(tour.getMaTour());

        HinhAnh secondaryHinhAnh = new HinhAnh();
        secondaryHinhAnh.setImg(secondaryImageUrl);
        hinhAnhRepository.save(secondaryHinhAnh);

        secondaryImage.setId(secondaryHinhAnh.getId());
        imagesRepository.save(secondaryImage);
    }

    public void updateSecondaryImageForTour(Tour tour, String secondaryImageUrl) {
        List<Images> images = imagesRepository.findByMaTour(tour.getMaTour());
        if (images.size() > 1) {
            Images image = images.get(1);
            HinhAnh hinhAnh = hinhAnhRepository.findById(image.getId()).orElse(null);
            if (hinhAnh != null) {
                hinhAnh.setImg(secondaryImageUrl);
                hinhAnhRepository.save(hinhAnh);
            }
        } else {
            addSecondaryImageToTour(tour, secondaryImageUrl);
        }
    }
    @Transactional(rollbackFor = Exception.class)
    public void addMainImageToTour(Tour tour, String mainImageUrl) {
        // Create Images entity for the main image URL and associate it with the tour
        Images mainImage = new Images();
        mainImage.setMaTour(tour.getMaTour());

        HinhAnh mainHinhAnh = new HinhAnh();
        mainHinhAnh.setImg(mainImageUrl);
        hinhAnhRepository.save(mainHinhAnh);

        mainImage.setId(mainHinhAnh.getId());
        imagesRepository.save(mainImage);
    }
    // Delete a product by its id
    public void deleteTour(String maTour) {
        Tour tour = tourRepository.findById(maTour)
                .orElseThrow(() -> new IllegalStateException("Tour with ID " + maTour + " does not exist."));

        // Xóa hình ảnh liên quan trong Images và HinhAnh
        List<Images> images = imagesRepository.findByMaTour(tour.getMaTour());
        for (Images image : images) {
            // Xóa từ Images
            imagesRepository.delete(image);

            // Xóa từ HinhAnh
            HinhAnh hinhAnh = hinhAnhRepository.findById(image.getId())
                    .orElseThrow(() -> new IllegalStateException("HinhAnh with ID " + image.getId() + " does not exist."));
            hinhAnhRepository.delete(hinhAnh);
        }

        // Xóa tour sau khi xóa hình ảnh
        tourRepository.delete(tour);
    }



//    public int applyPromotionAndGetNewPrice(String maTour, String maKM) {
//        Tour tour = tourRepository.findByMaTour(maTour);
//        if (tour == null) {
//            throw new IllegalArgumentException("Tour not found with maTour: " + maTour);
//        }
//
//        Optional<KhuyenMai> optionalKhuyenMai = khuyenMaiRespository.findByMaKM(maKM);
//        if (optionalKhuyenMai.isEmpty()) {
//            throw new IllegalArgumentException("KhuyenMai not found with maKM: " + maKM);
//        }
//
//        KhuyenMai khuyenMai = optionalKhuyenMai.get();
//
//        // Áp dụng chiết khấu vào giá tour dựa trên phần trăm khuyến mãi
//        int giaTour = tour.getGiaTour();
//        int phanTramKM = khuyenMai.getPhanTramKM();
//        int discountedPrice = giaTour - (giaTour * phanTramKM / 100);
//
//        return discountedPrice;
//    }

    public Map<String, Object> applyPromotionAndGetNewPrice(String maTour, String maKM) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new SecurityException("User is not authenticated");
        }
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        int currentUserId = userDetails.getUserId();
        Tour tour = tourRepository.findByMaTour(maTour);
        if (tour == null) {
            throw new IllegalArgumentException("Tour not found with maTour: " + maTour);
        }
        if (Objects.equals(tour.getLoaiTour().getMaLoaiTour(), "LT03")) {
            throw new IllegalArgumentException("Tour VIP is not eligible for discounts.");
        }
        Optional<KhuyenMai> optionalKhuyenMai = khuyenMaiRespository.findByMaKM(maKM);
        if (optionalKhuyenMai.isEmpty()) {
            throw new IllegalArgumentException("KhuyenMai not found with maKM: " + maKM);
        }

        KhuyenMai khuyenMai = optionalKhuyenMai.get();
        if (currentUserId != (khuyenMai.getTaiKhoan().getID())) { // Assuming KhuyenMai has a getUserId method
            throw new IllegalArgumentException("Promotion code does not belong to the current user");
        }

        // Áp dụng chiết khấu vào giá tour dựa trên phần trăm khuyến mãi
        int giaTour = tour.getGiaTour();
        int phanTramKM = khuyenMai.getPhanTramKM();
        int discountedPrice = giaTour - (giaTour * phanTramKM / 100);
        if(!khuyenMai.isSoLan()){
            throw new IllegalArgumentException("Promotion code has been used.");
        }

        // Chuẩn bị thông tin thời gian sử dụng mã khuyến mãi
        Map<String, Object> response = new HashMap<>();
        response.put("discountApplied", true);
        response.put("newPrice", discountedPrice);
        response.put("startDate", khuyenMai.getNgayBatDau());

        response.put("endDate", khuyenMai.getNgayKetThuc());

        return response;
    }
}