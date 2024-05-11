package it.sportandreview.review_from_host;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Log4j2
public class ReviewFromHostServiceImpl implements ReviewFromHostService{

    private final ReviewFromHostRepository reviewFromHostRepository;
    private final ReviewFromHostMapper reviewFromHostMapper;

    @Autowired
    public ReviewFromHostServiceImpl(ReviewFromHostRepository reviewFromHostRepository, ReviewFromHostMapper reviewFromHostMapper) {
        this.reviewFromHostRepository = reviewFromHostRepository;
        this.reviewFromHostMapper = reviewFromHostMapper;
    }
}
