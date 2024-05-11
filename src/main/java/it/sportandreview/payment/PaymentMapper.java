package it.sportandreview.payment;

import it.sportandreview.game_match.GameMatchMapper;
import it.sportandreview.payment_type.PaymentTypeMapper;
import it.sportandreview.player_user.PlayerUserMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses={Payment.class, PlayerUserMapper.class, GameMatchMapper.class, PaymentTypeMapper.class})
public interface PaymentMapper {

    PaymentDTO toDto(Payment payment);
    Payment toEntity(PaymentDTO paymentDTO);

}
