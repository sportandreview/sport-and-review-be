package it.sportandreview.photogallery;

import it.sportandreview.club.ClubMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses={Photogallery.class, ClubMapper.class})
public interface PhotogalleryMapper {

    PhotogalleryDTO toDto(Photogallery photogallery);
    Photogallery toEntity(PhotogalleryDTO PhotogalleryDto);

}
