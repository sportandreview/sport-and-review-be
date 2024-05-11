package it.sportandreview.brand;

import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring", uses={Brand.class})
public interface BrandMapper {



    BrandDTO toDto(Brand brand);

    Brand toEntity(BrandDTO brandDTO);

    List<BrandDTO> toDto(List<Brand> Brands);

    List<Brand> toEntity(List<BrandDTO> BrandsDTO);


}
