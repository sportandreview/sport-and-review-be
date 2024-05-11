package it.sportandreview.player_user;

import it.sportandreview.club.Club;
import it.sportandreview.field.Field;
import it.sportandreview.gender_type.GenderType;
import it.sportandreview.gender_type.GenderTypeDTO;
import it.sportandreview.gender_type.GenderTypeMapper;
import it.sportandreview.gender_type.GenderTypeRepository;
import it.sportandreview.highlight.Highlight;
import it.sportandreview.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses={User.class, GenderTypeMapper.class})
public abstract class PlayerUserMapper {

    @Autowired
    GenderTypeRepository genderTypeRepository;

    @Mapping(source = "favoriteClubs", target = "favoriteClubsId", qualifiedByName = "clubToId")
    @Mapping(source = "favoriteFields", target = "favoriteFieldsId", qualifiedByName = "fieldToId")
    @Mapping(source = "friends", target = "friendsId", qualifiedByName = "friendToId")
    @Mapping(source = "favoriteHighlights", target = "favoriteHighlightsId", qualifiedByName = "highlightToId")
    public abstract PlayerUserDTO toDto(User playerUser);

    @Mapping(source = "genderType", target = "genderType", qualifiedByName = "genderTypeDescriptionToGenderType")
    public abstract User toEntity(PlayerUserDTO playerUserDto);

    @Named("friendToId")
    public static Long friendToId(User b) {
        return b.getId();
    }

    @Named("highlightToId")
    public static Long highlightToId(Highlight b) {
        return b.getId();
    }

    @Named("clubToId")
    public static Long clubToId(Club b) {
        return b.getId();
    }

    @Named("fieldToId")
    public static Long fieldToId(Field b) {
        return b.getId();
    }

    @Named("genderTypeDescriptionToGenderType")
    public GenderType genderTypeDescriptionToGenderType(GenderTypeDTO genderTypeDTO) {
        GenderType genderType = genderTypeRepository.findByDescription(genderTypeDTO.getDescription());
        return genderType;
    }

}
