package it.sportandreview.field_size_type;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



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






