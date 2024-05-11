package it.sportandreview.fieal_size_type;

import it.sportandreview.exception.CreateEntityException;
import it.sportandreview.exception.NotFoundException;
import it.sportandreview.utils.Sha256Utils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;


@Service
@Log4j2
public class FieldSizeTypeServiceImpl implements FieldSizeTypeService {

    private final FieldSizeTypeRepository fieldSizeTypeRepository;
    private final FieldSizeTypeMapper fieldSizeTypeMapper;

    @Autowired
    public FieldSizeTypeServiceImpl(FieldSizeTypeRepository fieldSizeTypeRepository,
                                    FieldSizeTypeMapper fieldSizeTypeMapper) {
        this.fieldSizeTypeRepository = fieldSizeTypeRepository;
        this.fieldSizeTypeMapper = fieldSizeTypeMapper;
    }

}






