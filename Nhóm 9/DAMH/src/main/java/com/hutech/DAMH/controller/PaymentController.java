package com.hutech.DAMH.controller;

import com.hutech.DAMH.model.HoaDon;
import com.hutech.DAMH.model.TaiKhoan;
import com.hutech.DAMH.model.Tour;
import com.hutech.DAMH.other.MoMoSecurity;
import com.hutech.DAMH.other.PaymentRequest;
import com.hutech.DAMH.service.HoaDonService;
import com.hutech.DAMH.service.TaiKhoanService;
import com.hutech.DAMH.service.TourService;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PaymentController {
    private final TaiKhoanService taiKhoanService;
    private final HoaDonService hoaDonService;
    private final TourService tourService;

    public PaymentController(TaiKhoanService taiKhoanService, HoaDonService hoaDonService, TourService tourService) {
        this.taiKhoanService = taiKhoanService;
        this.hoaDonService = hoaDonService;
        this.tourService = tourService;
    }

    @GetMapping("/api/v1/payment/{maTour}/{sdt}/{diachi}/{adults}/{children}")
    public Map<String, String> payment(@PathVariable String maTour,
                                       @PathVariable String sdt,
                                       @PathVariable String diachi,
                                       @PathVariable String adults,
                                       @PathVariable String children,
                                       @RequestParam int amount,
                                       Authentication authentication, HttpSession session) throws Exception {
        String username = getCurrentUsername(authentication);
        if (username == null) {
            throw new Exception("Không thể lấy thông tin tài khoản người dùng hiện hành");
        }
        session.setAttribute("maTour", maTour);
        session.setAttribute("sdt", sdt);
        session.setAttribute("diachi", diachi);
        session.setAttribute("adults", adults);
        session.setAttribute("children", children);
        String endpoint = "https://test-payment.momo.vn/gw_payment/transactionProcessor";
        String partnerCode = "MOMOOJOI20210710";
        String accessKey = "iPXneGmrJH0G8FOP";
        String secretKey = "sFcbSGRSJjwGxwhhcEktCHWYUuTuPNDB";
        String orderInfo = "Thanh toán online";
        String returnUrl = "http://localhost:8080/api/v1/payment/callback";
        String notifyUrl = "http://localhost:8080/api/v1/payment/notify";
        String orderId = String.valueOf(new Date().getTime());
        if (orderId.length() > 10) {
            orderId = orderId.substring(orderId.length() - 10);
        }
        String requestId = String.valueOf(new Date().getTime());
        String extraData = "";

        try {
            String amountStr = String.valueOf(amount);

            String rawHash = "partnerCode=" + partnerCode +
                    "&accessKey=" + accessKey +
                    "&requestId=" + requestId +
                    "&amount=" + amountStr +
                    "&orderId=" + orderId +
                    "&orderInfo=" + orderInfo +
                    "&returnUrl=" + returnUrl +
                    "&notifyUrl=" + notifyUrl +
                    "&extraData=" + extraData;

            MoMoSecurity crypto = new MoMoSecurity();
            String signature = crypto.signSHA256(rawHash, secretKey);

            JSONObject message = new JSONObject();
            message.put("partnerCode", partnerCode);
            message.put("accessKey", accessKey);
            message.put("requestId", requestId);
            message.put("amount", amountStr);
            message.put("orderId", orderId);
            message.put("orderInfo", orderInfo);
            message.put("returnUrl", returnUrl);
            message.put("notifyUrl", notifyUrl);
            message.put("extraData", extraData);
            message.put("requestType", "captureMoMoWallet");
            message.put("signature", signature);
            message.put("maTour", maTour);
            message.put("phoneNumber", sdt);
            message.put("address", diachi);

            String response = PaymentRequest.sendPaymentRequest(endpoint, message.toString());
            JSONObject jsonResponse = new JSONObject(response);

            Map<String, String> result = new HashMap<>();
            System.out.println("Response from MoMo: " + response);

            if (jsonResponse.has("payUrl")) {
                result.put("payUrl", jsonResponse.getString("payUrl"));
            } else {
                result.put("message", "Không nhận được payUrl từ MoMo. Phản hồi: " + response);
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error processing payment", e);
        }
    }


    @GetMapping("/api/v1/payment/callback")
    public ModelAndView handlePaymentCallback(@RequestParam Map<String, String> queryParams, Model model, Authentication authentication, HttpSession session) throws Exception {
        // Lấy maTour từ session
        String maTour = (String) session.getAttribute("maTour");
        String sdt = (String) session.getAttribute("sdt");
        String diachi = (String) session.getAttribute("diachi");
        Object adultsObj = session.getAttribute("adults");
        Object childrenObj = session.getAttribute("children");

        int NguoiLon = adultsObj != null ? Integer.parseInt(adultsObj.toString()) : 0;
        int TreEm = childrenObj != null ? Integer.parseInt(childrenObj.toString()) : 0;

        // Kiểm tra maTour có tồn tại hay không
        if (maTour == null) {
            throw new Exception("maTour không tồn tại trong session");
        }

        // Ghi log các tham số nhận được từ MoMo
        System.out.println("Received callback with params: " + queryParams);

        String amount = queryParams.get("amount");
        String orderId = queryParams.get("orderId");

        String param = queryParams.toString().substring(0, queryParams.toString().indexOf("signature") - 1);
        param = java.net.URLDecoder.decode(param, StandardCharsets.UTF_8);
        String username = getCurrentUsername(authentication);

        if (username == null) {
            System.err.println("Cannot retrieve the current user's username.");
            throw new Exception("Không thể lấy thông tin tài khoản người dùng hiện hành");
        }

        System.out.println("Callback received for user: " + username);
        System.out.println("Amount: " + amount + ", Order ID: " + orderId + ", MaTour: " + maTour + ",sdt: " + sdt + ",DiaChi: " + diachi);
        ModelAndView modelAndView = new ModelAndView();
        if (param.contains("Bad request")) {
            modelAndView.setViewName("index/ThanhToanThatBai.html");
        } else {
            try {
                model.addAttribute("orderId", orderId);
                model.addAttribute("amount", amount);
                model.addAttribute("paymentMethod", "MoMo"); // Assuming MoMo is the payment method here
                model.addAttribute("paymentDateTime", new Date()); // You can adjust this based on your application logic
                savePaymentToHoaDon(Integer.parseInt(amount), orderId, username, maTour,sdt,diachi,NguoiLon,TreEm);
                modelAndView.setViewName("index/ThanhToanThanhCong.html");
            } catch (Exception e) {
                modelAndView.setViewName("index/ThanhToanThatBai.html");
            }
        }
        return modelAndView;
    }

    private String getCurrentUsername(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            } else if (principal instanceof OAuth2User) {
                return ((OAuth2User) principal).getAttribute("email");
            }
        }
        return null;
    }

    private void savePaymentToHoaDon(int amount, String orderId, String username, String maTour,String sdt,String DiaChi,int NguoiLon,int TreEm) throws Exception {
        TaiKhoan taiKhoan = taiKhoanService.findByTenTK(username)
                .orElseThrow(() -> new Exception("Không tìm thấy tài khoản với tên: " + username));

        Tour tour = tourService.getTourByMaTour(maTour);


        int soNguoiLonConLai = tour.getSoluong() - NguoiLon;
        if (soNguoiLonConLai < 0) {
            throw new Exception("Số lượng người lớn đã đặt vượt quá số lượng còn lại trong tour.");
        }
        tour.setSoluong(soNguoiLonConLai);
        tourService.saveTour(tour);
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMaHD(orderId);
        hoaDon.setTongTien(new BigDecimal(amount));
        hoaDon.setNgayLap(new Date());
        hoaDon.setId(taiKhoan.getID());
        hoaDon.setMaTour(maTour);
        hoaDon.setSdt(sdt);
        hoaDon.setDiaChi(DiaChi);
        hoaDon.setSoNguoiLon(NguoiLon);
        hoaDon.setSoTreEm(TreEm);
        hoaDonService.saveHoaDon(hoaDon);

        System.out.println("Saving HoaDon: " + hoaDon);
    }
}
