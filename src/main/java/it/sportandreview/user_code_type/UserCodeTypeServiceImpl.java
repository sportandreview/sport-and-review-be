package it.sportandreview.user_code_type;

import it.sportandreview.exception.CreateEntityException;
import it.sportandreview.exception.NotFoundException;
import it.sportandreview.game_match.GameMatch;
import it.sportandreview.user.User;
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
public class UserCodeTypeServiceImpl implements UserCodeTypeService {

    private final UserCodeTypeRepository userCodeTypeRepository;
    private final UserCodeTypeMapper userCodeTypeMapper;

    @Autowired
    public UserCodeTypeServiceImpl(UserCodeTypeRepository userCodeTypeRepository, UserCodeTypeMapper userCodeTypeMapper) {
        this.userCodeTypeRepository = userCodeTypeRepository;
        this.userCodeTypeMapper = userCodeTypeMapper;
    }

    @Override
    public Long create(UserCodeTypeDTO userCodeTypeDTO) {
        if (Objects.nonNull(userCodeTypeDTO.getId()) && userCodeTypeRepository.existsById(userCodeTypeDTO.getId())){
            throw new CreateEntityException(" userCodeType", "UserCodeType" + " exists into database");
        }
        // Setting UserCodeType UUID
        String uuid = StringUtils.isBlank(userCodeTypeDTO.getUuid()) ? Sha256Utils.getEncodedRandomUUID() : userCodeTypeDTO.getUuid();
        userCodeTypeDTO.setUuid(uuid);
        UserCodeType userCodeType = userCodeTypeRepository.save(userCodeTypeMapper.toEntity(userCodeTypeDTO));
        log.debug("UserCodeType created");
        return userCodeType.getId();
    }

    @Override
    public List<UserCodeTypeDTO> findAll() {
        return userCodeTypeRepository.findAll().stream().map(userCodeTypeMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UserCodeTypeDTO findById(Long userCodeTypeId) {
        return userCodeTypeRepository.findById(userCodeTypeId).map(userCodeTypeMapper::toDto).
                orElseThrow(() -> new NotFoundException("UserCodeType", "UserCodeType" + "not exists into database"));
    }
}
