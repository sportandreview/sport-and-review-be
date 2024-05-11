package it.sportandreview.auto_evaluation_sport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/autoEvaluationSportLevels")
public class AutoEvaluationSportLevelController {

    private final AutoEvaluationSportLevelService service;

    @Autowired
    public AutoEvaluationSportLevelController(AutoEvaluationSportLevelService service) {
        this.service = service;
    }


}
