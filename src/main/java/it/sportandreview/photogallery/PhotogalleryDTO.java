package it.sportandreview.photogallery;

import it.sportandreview.base.BaseDTO;
import it.sportandreview.club.ClubDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;



@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class PhotogalleryDTO extends BaseDTO {

    private String image;
    private ClubDTO club;
}
