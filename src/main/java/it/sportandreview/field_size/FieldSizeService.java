package it.sportandreview.field_size;



import java.util.List;

public interface FieldSizeService {

    Long create(FieldSizeDTO fieldSizeDTO);

    FieldSizeDTO update(FieldSizeDTO fieldSizeDTO);

    List<FieldSizeDTO> findAll();

    FieldSizeDTO findById(Long fieldSizeId);


}
