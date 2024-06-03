package it.sportandreview.services;

import it.sportandreview.club.Club;
import it.sportandreview.club.ClubRepository;
import it.sportandreview.exception.CreateEntityException;
import it.sportandreview.exception.NotFoundException;
import it.sportandreview.util.Sha256Utils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@Log4j2
public class ServicesServiceImpl implements ServicesService {

    private final ServicesRepository servicesRepository;
    private final ServicesMapper servicesMapper;
    private final ClubRepository clubRepository;

    @Autowired
    public ServicesServiceImpl(ServicesRepository servicesRepository, ServicesMapper servicesMapper, ClubRepository clubRepository) {

        this.servicesRepository = servicesRepository;
        this.servicesMapper = servicesMapper;
        this.clubRepository = clubRepository;
    }

    @Override
    public Long create(ServicesDTO servicesDTO) {
        // Setting Services UUID
        String uuid = StringUtils.isBlank(servicesDTO.getUuid()) ? Sha256Utils.getEncodedRandomUUID() : servicesDTO.getUuid();
        servicesDTO.setUuid(uuid);

        if (Objects.nonNull(servicesDTO.getId()) && servicesRepository.existsById(servicesDTO.getId())) {
            throw new CreateEntityException("Services", "Services" + " exists into database");
        }
        Services services = servicesRepository.save(servicesMapper.toEntity(servicesDTO));
        log.debug("Services created");
        return services.getId();
    }

    @Override
    public ServicesDTO update(ServicesDTO servicesDTO) {
        if (Objects.isNull(servicesDTO.getId()) || !servicesRepository.existsById(servicesDTO.getId())) {
            throw new NotFoundException("Services", "Services" + "not found");
        }

        Services saved = servicesRepository.save(servicesMapper.toEntity(servicesDTO));
        log.debug("Services updated");

        ServicesDTO savedDTO = servicesMapper.toDto(saved);
        return savedDTO;
    }

    @Override
    public List<ServicesDTO> findByClubsId(List<Long> clubId) {
        List<Club> clubs = clubRepository.findAllById(clubId);
        if (!CollectionUtils.isEmpty(clubs)) {
            List<Services> services = new ArrayList<>();
            clubs.forEach(club -> {
                services.addAll(club.getServices());
            });
            return services.stream().map(servicesMapper::toDto).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public List<ServicesDTO> findAll() {
        return servicesRepository.findAll().stream().map(servicesMapper::toDto).collect(Collectors.toList());
    }
}
