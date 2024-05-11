package it.sportandreview.join_request;

import it.sportandreview.exception.CreateEntityException;
import it.sportandreview.exception.NotFoundException;
import it.sportandreview.utils.Sha256Utils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@Log4j2
public class JoinRequestServiceImpl implements JoinRequestService {

    private final JoinRequestRepository joinRequestRepository;
    private final JoinRequestMapper joinRequestMapper;

    @Autowired
    public JoinRequestServiceImpl(JoinRequestRepository joinRequestRepository, JoinRequestMapper joinRequestMapper) {
        this.joinRequestRepository = joinRequestRepository;
        this.joinRequestMapper = joinRequestMapper;
    }

    @Override
    public Long create(JoinRequestDTO joinRequestDTO) {
        if (Objects.nonNull(joinRequestDTO.getId()) && joinRequestRepository.existsById(joinRequestDTO.getId())){
            throw new CreateEntityException("JoinRequest", "JoinRequest" + " exists into database");
        }
        // Setting JoinRequest UUID
        String uuid = StringUtils.isBlank(joinRequestDTO.getUuid()) ? Sha256Utils.getEncodedRandomUUID() : joinRequestDTO.getUuid();
        joinRequestDTO.setUuid(uuid);
        JoinRequest joinRequest = joinRequestRepository.save(joinRequestMapper.toEntity(joinRequestDTO));
        log.debug("JoinRequest created");
        return joinRequest.getId();
    }

    @Override
    public List<JoinRequestDTO> findAll() {
        return joinRequestRepository.findAll().stream().map(joinRequestMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public JoinRequestDTO findById(Long joinRequestId) {
        return joinRequestRepository.findById(joinRequestId).map(joinRequestMapper::toDto).
                orElseThrow(() -> new NotFoundException("join request", "JoinRequest" + "not exists into database"));
    }
}
