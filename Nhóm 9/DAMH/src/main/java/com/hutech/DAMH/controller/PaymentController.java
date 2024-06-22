package com.hutech.DAMH.controller;

import com.hutech.DAMH.other.MoMoSecurity;
import com.hutech.DAMH.other.PaymentRequest;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PaymentController {

    @GetMapping("/api/v1/payment")
    public Map<String, String> payment(@RequestParam int amount) throws Exception {
        String endpoint = "https://test-payment.momo.vn/gw_payment/transactionProcessor";
        String partnerCode = "MOMOOJOI20210710";
        String accessKey = "iPXneGmrJH0G8FOP";
        String secretKey = "sFcbSGRSJjwGxwhhcEktCHWYUuTuPNDB";
        String orderInfo = "Thanh toán online";
        String returnUrl = "http://localhost:8080/DuLichViet/Home";
        String notifyUrl = "http://localhost:8080/DuLichViet/api/v1/callback";
        String orderId = String.valueOf(new Date().getTime());
        String requestId = String.valueOf(new Date().getTime());
        String extraData = "";

        try {
            // Chuyển amount thành chuỗi
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
            message.put("amount", amountStr);  // Đảm bảo amount là chuỗi
            message.put("orderId", orderId);
            message.put("orderInfo", orderInfo);
            message.put("returnUrl", returnUrl);
            message.put("notifyUrl", notifyUrl);
            message.put("extraData", extraData);
            message.put("requestType", "captureMoMoWallet");
            message.put("signature", signature);

            String response = PaymentRequest.sendPaymentRequest(endpoint, message.toString());
            JSONObject jsonResponse = new JSONObject(response);

            Map<String, String> result = new HashMap<>();

            // In phản hồi từ server
            System.out.println("Response from MoMo: " + response);

            // Kiểm tra nếu jsonResponse có chứa trường 'payUrl'
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
}
