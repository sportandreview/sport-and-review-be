package it.sportandreview.filter;

import it.sportandreview.club.Club;
import it.sportandreview.club.ClubMapper;
import it.sportandreview.club.ClubRepository;
import it.sportandreview.exception.NotFoundException;
import it.sportandreview.field.Field;
import it.sportandreview.field.FieldMapper;
import it.sportandreview.field.FieldRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses={Filter.class, FieldMapper.class, ClubMapper.class})
public abstract class FilterMapper {
    @Autowired
    private ClubRepository clubRepository;
    @Autowired
    private FieldRepository fieldRepository;
    @Mapping(target = "clubId", source = "club.id")
    @Mapping(target = "fieldId", source = "field.id")
    public abstract FilterDTO toDto(Filter filter);
    @Mapping(source = "clubId", target = "club", qualifiedByName = "convertClubIdToClub")
    @Mapping(source = "fieldId", target = "field", qualifiedByName = "fieldIdToField")
     public abstract Filter toEntity(FilterDTO filterDto);

    @Named("convertClubIdToClub")
    public Club convertClubIdToClub(Long clubId) {
        Club club = clubRepository.findById(clubId).
                orElseThrow(() -> new NotFoundException("club", "Club" + " not exists into database"));
        return club;
    }

    @Named("fieldIdToField")
    public Field fieldIdToField(Long fieldId) {
        Field field = fieldRepository.findById(fieldId).
                orElseThrow(() -> new NotFoundException("field", "Field" + " not exists into database"));
        return field;
    }

}
