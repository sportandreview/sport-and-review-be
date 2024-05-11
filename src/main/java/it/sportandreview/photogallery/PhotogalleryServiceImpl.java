package it.sportandreview.photogallery;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Log4j2
public class PhotogalleryServiceImpl implements PhotogalleryService{

    private final PhotogalleryRepository photogalleryRepository;
    private final PhotogalleryMapper photogalleryMapper;

    @Autowired
    public PhotogalleryServiceImpl(PhotogalleryRepository photogalleryRepository, PhotogalleryMapper photogalleryMapper) {
        this.photogalleryRepository = photogalleryRepository;
        this.photogalleryMapper = photogalleryMapper;
    }
}
