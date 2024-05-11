package it.sportandreview.payment;

import it.sportandreview.base.IndexedEntity;
import it.sportandreview.payment_type.PaymentType;
import it.sportandreview.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "app_payment")
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Payment extends IndexedEntity {

    @Column(name = "is_partial")
    private Boolean isPartial;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "partial_amount")
    private Double partialAmount;

    @Column(name = "transaction_code")
    private String transactionCode;

    @Column(name = "booking_policy")
    private String bookingPolicy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_user_id", referencedColumnName = "id")
    @ToString.Exclude
    private User payedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_type_id", referencedColumnName = "id")
    @ToString.Exclude
    private PaymentType paymentType;
}
