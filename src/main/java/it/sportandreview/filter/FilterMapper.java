package it.sportandreview.filter;

import it.sportandreview.club.ClubMapper;
import it.sportandreview.field.FieldMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses={Filter.class, FieldMapper.class, ClubMapper.class})
public interface FilterMapper {

    FilterDTO toDto(Filter filter);
    Filter toEntity(FilterDTO filterDto);

}
