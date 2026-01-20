package com.git.Payment.Controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.git.Payment.Entity.Payment;
import com.git.Payment.Service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // Create Razorpay Order
    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody Map<String, Object> payload) {
        try {
            Long amount = Long.valueOf(payload.get("amount").toString());
            String currency = payload.getOrDefault("currency", "INR").toString();

            Map<String, Object> orderResponse = paymentService.createRazorpayOrder(amount, currency);
            return ResponseEntity.ok(orderResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating order: " + e.getMessage());
        }
    }

    // Verify
    @PostMapping("/verify")
    public ResponseEntity<?> verifyPaymentEntity(
            @RequestBody Map<String, String> payload) {
        String orderId = payload.get("razorpay_order_id");
        String paymentId = payload.get("razorpay_payment_id");
        String signature = payload.get("razorpay_signature");

        System.out.println("ORDER_ID   = " + orderId);
        System.out.println("PAYMENT_ID = " + paymentId);
        System.out.println("SIGNATURE  = " + signature);
        boolean valid = paymentService.verifyPaymentSignature(
                orderId,
                paymentId,
                signature);

        if (!valid) {
            paymentService.markPaymentFailed(orderId);
            return ResponseEntity.badRequest().body("Payment Verification Failed");
        }

        paymentService.markPaymentSuccess(orderId, paymentId);
        return ResponseEntity.ok("Payment Verified Successfully");
    }

    // GET Payment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    // GET Payment by Razorpay Order ID
    @GetMapping("/order/{orderId}")
    public ResponseEntity<Payment> getPaymenrByRazorpayOrderId(@PathVariable String orderId) {
        return ResponseEntity.ok(
                paymentService.getPaymentByOrderId(orderId));
    }

    // Razorpay Webhook Endpoint
    @PostMapping("/webhook")
    public ResponseEntity<String> handleWebhook(
            @RequestHeader("X-Razorpay-Signature") String signature,
            @RequestBody String payload) {
        boolean valid = paymentService.verifyWebhookSignature(payload, signature);

        if (!valid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid webhook signature");
        }

        paymentService.processWebhookEvent(payload);
        return ResponseEntity.ok("Webhook processed");
    }

}
