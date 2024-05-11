package it.sportandreview.photogallery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/photogalleries")
public class PhotogalleryController {

    private final PhotogalleryService service;

    @Autowired
    public PhotogalleryController(PhotogalleryService service) {
        this.service = service;
    }
}
