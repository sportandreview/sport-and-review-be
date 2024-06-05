package it.sportandreview.payment;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import it.sportandreview.dto.ApiResponseDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService service;

    @Autowired
    public PaymentController(PaymentService service) {
        this.service = service;
    }
    @PostMapping
    @Operation(summary = "Create new payment")
    public ResponseEntity<ApiResponseDTO<PaymentDTO>> create(@Parameter(name = "paymentDTO") @RequestBody PaymentDTO paymentDTO) {
        ApiResponseDTO<PaymentDTO> response = ApiResponseDTO.<PaymentDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Payment creata con successo")
                .result(service.create(paymentDTO))
                .build();
        return ResponseEntity.ok(response);
    }
}
