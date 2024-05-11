package it.sportandreview.photogallery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotogalleryRepository extends JpaRepository<Photogallery, Long> {

}
