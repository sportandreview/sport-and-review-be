package it.sportandreview.slot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/slot")
public class SlotController {

    private final SlotService service;

    @Autowired
    public SlotController(SlotService service) {
        this.service = service;
    }

}
