package it.sportandreview.review_from_host;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/reviewFromHosts")
public class ReviewFromHostController {

    private final ReviewFromHostService service;

    @Autowired
    public ReviewFromHostController(ReviewFromHostService service) {
        this.service = service;
    }
}
