package it.sportandreview.filter;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Log4j2
public class FilterServiceImpl implements FilterService {

    private final FilterRepository FilterRepository;
    private final FilterMapper filterMapper;

    @Autowired
    public FilterServiceImpl(FilterRepository FilterRepository, FilterMapper filterMapper) {
        this.FilterRepository = FilterRepository;
        this.filterMapper = filterMapper;
    }



}
