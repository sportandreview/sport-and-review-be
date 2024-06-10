package it.sportandreview.field;

import it.sportandreview.club.Club;
import it.sportandreview.club.ClubMapper;
import it.sportandreview.club.ClubRepository;
import it.sportandreview.exception.NotFoundException;
import it.sportandreview.ground_type.GroundType;
import it.sportandreview.ground_type.GroundTypeMapper;
import it.sportandreview.ground_type.GroundTypeRepository;
import it.sportandreview.services.ServicesMapper;
import it.sportandreview.sport.Sport;
import it.sportandreview.sport.SportRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses={Field.class, GroundTypeMapper.class, ClubMapper.class, ServicesMapper.class})
public abstract class FieldMapper {
    @Autowired
    private GroundTypeRepository groundTypeRepository;
    @Autowired
    private ClubRepository clubRepository;
    @Autowired
    private SportRepository sportRepository;
    @Mapping(target = "groundTypeId", source = "groundType.id")
    @Mapping(target = "clubId", source = "club.id")
    @Mapping(target = "sportId", source = "sport.id")
    public abstract FieldDTO toDto(Field field);
    @Mapping(source = "groundTypeId", target = "groundType", qualifiedByName = "groundTypeIdToGroundType")
    @Mapping(source = "clubId", target = "club", qualifiedByName = "clubIdToClub")
    @Mapping(source = "sportId", target = "sport", qualifiedByName = "convertSportIdToSport")
    public abstract Field toEntity(FieldDTO fieldDto);
    @Mapping(target = "groundTypeId", source = "groundType.id")
    @Mapping(target = "sportId", source = "sport.id")
    public abstract FieldReducedDTO toDtoReduced(Field field);
    @Mapping(source = "groundTypeId", target = "groundType", qualifiedByName = "groundTypeIdToGroundType")
    @Mapping(source = "sportId", target = "sport", qualifiedByName = "convertSportIdToSport")
    public abstract Field toEntityReduced(FieldReducedDTO fieldDto);

    @Named("groundTypeIdToGroundType")
    public GroundType groundTypeIdToGroundType(Long groundTypeId) {
        GroundType groundType = groundTypeRepository.findById(groundTypeId).
                orElseThrow(() -> new NotFoundException("groundType", "GroundType" + " not exists into database"));
        return groundType;
    }

    @Named("clubIdToClub")
    public Club clubIdToClub(Long clubId) {
        Club club = clubRepository.findById(clubId).
                orElseThrow(() -> new NotFoundException("club", "Club" + " not exists into database"));
        return club;
    }

    @Named("convertSportIdToSport")
    public Sport convertSportIdToSport(Long sportId) {
        Sport sport = sportRepository.findById(sportId).
                orElseThrow(() -> new NotFoundException("sport", "Sport" + " not exists into database"));
        return sport;
    }

}
