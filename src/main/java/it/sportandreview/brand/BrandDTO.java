package it.sportandreview.brand;

import it.sportandreview.base.BaseDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;



@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class BrandDTO extends BaseDTO {

    private String description;
    private Long sportId;
}
