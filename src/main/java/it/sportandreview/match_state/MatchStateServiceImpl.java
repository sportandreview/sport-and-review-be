package it.sportandreview.match_state;

import it.sportandreview.exception.NotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Log4j2
public class MatchStateServiceImpl implements MatchStateService {

    private final MatchStateRepository matchStateRepository;
    private final MatchStateMapper matchStateMapper;

    @Autowired
    public MatchStateServiceImpl(MatchStateRepository matchStateRepository, MatchStateMapper matchStateMapper) {
        this.matchStateRepository = matchStateRepository;
        this.matchStateMapper = matchStateMapper;
    }

    @Override
    public List<MatchStateDTO> findAll() {
        return matchStateRepository.findAll().stream().map(matchStateMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public MatchState findById(Long matchStateId) {
        return matchStateRepository.findById(matchStateId).
                orElseThrow(() -> new NotFoundException("match state", "MatchState" + "not exists into database"));
    }
}
