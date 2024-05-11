package it.sportandreview.booked_slot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/bookedSlot")
public class BookedSlotController {

    private final BookedSlotService service;

    @Autowired
    public BookedSlotController(BookedSlotService service) {
        this.service = service;
    }

}
