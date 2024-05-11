package it.sportandreview.ground_type;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class GroundTypeServiceImpl implements GroundTypeService {

    private final GroundTypeRepository groundTyperepository;
    private final GroundTypeMapper groundTypemapper;

    @Autowired
    public GroundTypeServiceImpl(GroundTypeRepository groundTyperepository, GroundTypeMapper groundTypemapper) {
        this.groundTyperepository = groundTyperepository;
        this.groundTypemapper = groundTypemapper;
    }

    @Override
    public List<GroundTypeDTO> findAll() {
        return groundTyperepository.findAll().stream().map(groundTypemapper::toDto).collect(Collectors.toList());
    }
}
