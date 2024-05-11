package it.sportandreview.sport_equipment;

import it.sportandreview.brand.Brand;
import it.sportandreview.brand.BrandMapper;
import it.sportandreview.brand.BrandRepository;
import it.sportandreview.exception.NotFoundException;
import it.sportandreview.sport_equipment_type.SportEquipmentType;
import it.sportandreview.sport_equipment_type.SportEquipmentTypeRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Mapper(componentModel = "spring", uses={SportEquipment.class, BrandMapper.class, SportEquipmentMapper.class})
public abstract class SportEquipmentMapper {
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private SportEquipmentTypeRepository sportEquipmentTypeRepository;

    @Mapping(source = "brand.id", target = "brandId")
    @Mapping(source = "sportEquipmentType.id", target = "sportEquipmentTypeId")
    public abstract SportEquipmentDTO toDto(SportEquipment sportEquipment);

    @Mapping(source = "brandId", target = "brand", qualifiedByName = "brandIdToBrand")
    @Mapping(source = "sportEquipmentTypeId", target = "sportEquipmentType", qualifiedByName = "sportEquipmentTypeIdToSportEquipmentType")
    public abstract SportEquipment toEntity(SportEquipmentDTO sportEquipmentDTO);

    @Mapping(source = "brand.id", target = "brandId")
    @Mapping(source = "sportEquipmentType.id", target = "sportEquipmentTypeId")
    public abstract List<SportEquipmentDTO> toDto(List<SportEquipment> sportEquipments);

    @Mapping(source = "brandId", target = "brand", qualifiedByName = "brandIdToBrand")
    @Mapping(source = "sportEquipmentTypeId", target = "sportEquipmentType", qualifiedByName = "sportEquipmentTypeIdToSportEquipmentType")
    public abstract List<SportEquipment> toEntity(List<SportEquipmentDTO> SportEquipmentDTO);


    @Named("brandIdToBrand")
    public Brand brandIdToBrand(Long brandId) {
        Brand brand = brandRepository.findById(brandId).
                orElseThrow(() -> new NotFoundException("brand", "Brand" + " not exists into database"));
        return brand;
    }

    @Named("sportEquipmentTypeIdToSportEquipmentType")
    public SportEquipmentType sportEquipmentTypeIdToSportEquipmentType(Long sportEquipmentTypeId) {
        SportEquipmentType sportEquipmentType = sportEquipmentTypeRepository.findById(sportEquipmentTypeId).
                orElseThrow(() -> new NotFoundException("sportEquipmentType", "SportEquipmentType" + " not exists into database"));
        return sportEquipmentType;
    }

}
