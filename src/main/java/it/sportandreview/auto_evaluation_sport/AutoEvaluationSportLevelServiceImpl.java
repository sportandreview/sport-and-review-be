package it.sportandreview.auto_evaluation_sport;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
@Log4j2
public class AutoEvaluationSportLevelServiceImpl implements AutoEvaluationSportLevelService {

    private final AutoEvaluationSportLevelRepository autoEvaluationSportLevelRepository;
    private final AutoEvaluationSportLevelMapper autoEvaluationSportLevelMapper;

    @Autowired
    public AutoEvaluationSportLevelServiceImpl(AutoEvaluationSportLevelRepository autoEvaluationSportLevelRepository,
                                     AutoEvaluationSportLevelMapper autoEvaluationSportLevelMapper) {
        this.autoEvaluationSportLevelRepository = autoEvaluationSportLevelRepository;
        this.autoEvaluationSportLevelMapper = autoEvaluationSportLevelMapper;
    }

}






