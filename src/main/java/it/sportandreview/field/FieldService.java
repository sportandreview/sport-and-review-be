package it.sportandreview.field;



import java.util.List;
import java.util.Set;

public interface FieldService {

    long create(FieldDTO fieldDTO);

    FieldDTO update(FieldDTO fieldDTO);

    FieldDTO findById(Long id);

    List<FieldDTO> findByClubId(Long clubId);

    Set<FieldDTO> findByClubIdOrderByRatingDesc(Long clubId);

    PagingFieldDTO findAll(Integer pageSize, Integer pageNumber);
}
