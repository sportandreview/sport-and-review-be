package it.sportandreview.brand;



import java.util.List;

public interface BrandService {

    Long create(BrandDTO brandDto);

    BrandDTO update(BrandDTO brandDto);

    List<BrandDTO> findAll();

    BrandDTO findById(Long brandId);


}
