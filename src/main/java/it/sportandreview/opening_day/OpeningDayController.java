package it.sportandreview.opening_day;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/openingDays")
public class OpeningDayController {

    private final OpeningDayService service;

    @Autowired
    public OpeningDayController(OpeningDayService service) {
        this.service = service;
    }
}
