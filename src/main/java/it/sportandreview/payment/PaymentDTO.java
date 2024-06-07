package it.sportandreview.payment;

import it.sportandreview.base.BaseDTO;
import it.sportandreview.payment_type.PaymentTypeDTO;
import it.sportandreview.player_user.PlayerUserDTO;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "specificare se si vuole fare un pagamento parziale o meno")
    private Boolean isPartial;
    @NotNull(message = "l'importo è obbligatorio!")
    private Double amount;
    private Double partialAmount;
    private String transactionCode;
    private String bookingPolicy;
    @NotNull(message = "il player pagante")
    private PlayerUserDTO payedBy;
    @NotNull(message = "il tipo di pagamento è obbligatorio!")
    private PaymentTypeDTO paymentType;


}
