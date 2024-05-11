package it.sportandreview.highlight;


import java.util.List;

public interface HighlightService {

    Long create(HighlightDTO highlightDto);

    HighlightDTO update(HighlightDTO highlightDto);

    List<HighlightDTO> findAll();

    HighlightDTO findById(Long id);


}
