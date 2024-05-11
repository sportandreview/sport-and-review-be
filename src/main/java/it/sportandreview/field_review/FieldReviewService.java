package it.sportandreview.field_review;


import java.util.List;

public interface FieldReviewService {

    Long create(FieldReviewDTO fieldReviewDTO);

    FieldReviewDTO update(FieldReviewDTO fieldReviewDTO);

    List<FieldReviewDTO> findAll();

    FieldReviewDTO findById(Long fieldReviewId);

}
