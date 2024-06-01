package it.sportandreview.team;

import it.sportandreview.exception.CreateEntityException;
import it.sportandreview.exception.NotFoundException;
import it.sportandreview.util.Sha256Utils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@Log4j2
public class TeamServiceImpl implements TeamService{

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
    }

    @Override
    public Long create(TeamDTO teamDto) {
        if (Objects.nonNull(teamDto.getId()) && teamRepository.existsById(teamDto.getId())){
            throw new CreateEntityException("team", "Team" + " exists into database");
        }
        // Setting PlayerUser UUID
        String uuid = StringUtils.isBlank(teamDto.getUuid()) ? Sha256Utils.getEncodedRandomUUID() : teamDto.getUuid();
        teamDto.setUuid(uuid);
        Team team = teamRepository.save(teamMapper.toEntity(teamDto));
        log.debug("Team created");
        return team.getId();
    }


    @Override
    public TeamDTO update(TeamDTO teamDto) {
        if (Objects.isNull(teamDto.getId()) || !teamRepository.existsById(teamDto.getId())) {
            throw new NotFoundException("Team", "Team" + " not found");
        }

        Team team = teamRepository.save(teamMapper.toEntity(teamDto));
        log.debug("Team updated");

        TeamDTO savedDTO = teamMapper.toDto(team);
        return savedDTO;
    }
}
