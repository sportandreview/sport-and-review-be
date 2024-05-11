package it.sportandreview.user_otp;

import it.sportandreview.exception.NotFoundException;
import it.sportandreview.user_code_type.UserCodeType;
import it.sportandreview.user_code_type.UserCodeTypeMapper;
import it.sportandreview.user_code_type.UserCodeTypeRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring", uses={UserOtp.class, UserCodeTypeMapper.class})
public abstract class UserOtpMapper {
    @Autowired
    UserCodeTypeRepository userCodeTypeRepository;
    @Mapping(target = "userCodeTypeId", source = "userCodeType.id")
    public abstract UserOtpDTO toDto(UserOtp userOtp);
    @Mapping(source = "userCodeTypeId", target = "userCodeType", qualifiedByName = "userCodeTypeIdToUserCodeType")
    public abstract UserOtp toEntity(UserOtpDTO userOtpDto);

    @Named("userCodeTypeIdToUserCodeType")
    public UserCodeType userCodeTypeIdToUserCodeType(Long userCodeTypeId) {
        UserCodeType userCodeType = userCodeTypeRepository.findById(userCodeTypeId).
                orElseThrow(() -> new NotFoundException("userCodeType", "UserCodeType" + " not exists into database"));
        return userCodeType;
    }
}
