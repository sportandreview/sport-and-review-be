package it.sportandreview.payment;

import it.sportandreview.base.BaseDTO;
import it.sportandreview.game_match.GameMatchDTO;
import it.sportandreview.payment_type.PaymentTypeDTO;
import it.sportandreview.player_user.PlayerUserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class PaymentDTO extends BaseDTO {

    private Boolean isPartial;
    private Double amount;
    private Double partialAmount;
    private String transactionCode;
    private String bookingPolicy;
    private PlayerUserDTO payedBy;
    private PaymentTypeDTO paymentType;


}
