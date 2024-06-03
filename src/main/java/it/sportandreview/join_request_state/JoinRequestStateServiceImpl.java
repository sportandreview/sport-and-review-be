package it.sportandreview.join_request_state;

import it.sportandreview.exception.CreateEntityException;
import it.sportandreview.exception.NotFoundException;
import it.sportandreview.util.Sha256Utils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@Log4j2
public class JoinRequestStateServiceImpl implements JoinRequestStateService {

    private final JoinRequestStateRepository joinRequestStateRepository;
    private final JoinRequestStateMapper joinRequestStateMapper;

    @Autowired
    public JoinRequestStateServiceImpl(JoinRequestStateRepository joinRequestStateRepository, JoinRequestStateMapper joinRequestStateMapper) {
        this.joinRequestStateRepository = joinRequestStateRepository;
        this.joinRequestStateMapper = joinRequestStateMapper;
    }

    @Override
    public Long create(JoinRequestStateDTO joinRequestStateDTO) {
        if (Objects.nonNull(joinRequestStateDTO.getId()) && joinRequestStateRepository.existsById(joinRequestStateDTO.getId())){
            throw new CreateEntityException("JoinRequestState", "JoinRequestState" + " exists into database");
        }
        // Setting JoinRequestState UUID
        String uuid = StringUtils.isBlank(joinRequestStateDTO.getUuid()) ? Sha256Utils.getEncodedRandomUUID() : joinRequestStateDTO.getUuid();
        joinRequestStateDTO.setUuid(uuid);
        JoinRequestState joinRequestState = joinRequestStateRepository.save(joinRequestStateMapper.toEntity(joinRequestStateDTO));
        log.debug("JoinRequestState created");
        return joinRequestState.getId();
    }

    @Override
    public List<JoinRequestStateDTO> findAll() {
        return joinRequestStateRepository.findAll().stream().map(joinRequestStateMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public JoinRequestStateDTO findById(Long joinRequestStateId) {
        return joinRequestStateRepository.findById(joinRequestStateId).map(joinRequestStateMapper::toDto).
                orElseThrow(() -> new NotFoundException("join request state", "JoinRequestState" + "not exists into database"));
    }
}
