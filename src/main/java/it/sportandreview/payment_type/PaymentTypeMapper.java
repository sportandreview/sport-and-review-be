package it.sportandreview.payment_type;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses={PaymentType.class})
public interface PaymentTypeMapper {

    PaymentTypeDTO toDto(PaymentType paymentType);
    PaymentType toEntity(PaymentTypeDTO paymentTypeDTO);

}
