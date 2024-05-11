package it.sportandreview.user_code_type;


import java.util.List;

public interface UserCodeTypeService {

    Long create(UserCodeTypeDTO userCodeTypeDTO);

    List<UserCodeTypeDTO> findAll();

    UserCodeTypeDTO findById(Long userCodeTypeId);
}
