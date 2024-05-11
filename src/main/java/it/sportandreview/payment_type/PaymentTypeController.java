package it.sportandreview.payment_type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/paymentTypes")
public class PaymentTypeController {

    private final PaymentTypeService service;

    @Autowired
    public PaymentTypeController(PaymentTypeService service) {
        this.service = service;
    }
}
