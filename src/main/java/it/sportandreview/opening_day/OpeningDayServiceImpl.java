package it.sportandreview.opening_day;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Log4j2
public class OpeningDayServiceImpl implements OpeningDayService {

    private final OpeningDayRepository openingDayRepository;
    private final OpeningDayMapper openingDayMapper;

    @Autowired
    public OpeningDayServiceImpl(OpeningDayRepository openingDayRepository, OpeningDayMapper openingDayMapper) {
        this.openingDayRepository = openingDayRepository;
        this.openingDayMapper = openingDayMapper;
    }
}
