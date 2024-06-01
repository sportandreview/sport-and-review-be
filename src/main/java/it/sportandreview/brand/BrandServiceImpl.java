package it.sportandreview.brand;

import it.sportandreview.exception.CreateEntityException;
import it.sportandreview.exception.NotFoundException;
import it.sportandreview.util.Sha256Utils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@Log4j2
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper BrandMapper;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository,
                            BrandMapper brandMapper) {
        this.brandRepository = brandRepository;
        this.BrandMapper = brandMapper;
    }

    @Override
    public Long create(BrandDTO brandDto) {
        if (Objects.nonNull(brandDto.getId()) && brandRepository.existsById(brandDto.getId())){
            throw new CreateEntityException("Brand", "Brand" + " exists into database");
        }
        // Setting Brand UUID
        String uuid = StringUtils.isBlank(brandDto.getUuid()) ? Sha256Utils.getEncodedRandomUUID() : brandDto.getUuid();
        brandDto.setUuid(uuid);
        Brand brand = brandRepository.save(BrandMapper.toEntity(brandDto));
        log.debug("Brand created");
        return brand.getId();
    }

    @Override
    public BrandDTO update(BrandDTO brandDto) {
        if (Objects.isNull(brandDto.getId()) || !brandRepository.existsById(brandDto.getId())) {
            throw new NotFoundException("Brand", "Brand" + " not found");
        }
        Brand saved = brandRepository.save(BrandMapper.toEntity(brandDto));
        log.debug("Brand updated");
        BrandDTO savedDTO = BrandMapper.toDto(saved);
        return savedDTO;
    }
    @Override
    public List<BrandDTO> findAll() {
        return brandRepository.findAll().stream().map(BrandMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public BrandDTO findById(Long brandId) {
        return brandRepository.findById(brandId).map(BrandMapper::toDto).
                orElseThrow(() -> new NotFoundException("brand", "Brand" + "not exists into database"));
    }
}






