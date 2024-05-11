package it.sportandreview.owner;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses={Owner.class})
public interface OwnerMapper {

    OwnerDTO toDto(Owner owner);
    Owner toEntity(OwnerDTO ownerDTO);

}
