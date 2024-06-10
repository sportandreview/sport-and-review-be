package it.sportandreview.club_review;

import it.sportandreview.base.BaseDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class ClubReviewDTO extends BaseDTO {
    @NotNull(message = "L'id del player è obbligatorio")
    private Long playerUserId;
    @NotNull(message = "L'id del club è obbligatorio")
    private Long clubId;
    @NotNull(message = "voto accoglienza è obbligatorio")
    private Double welcomeServices;
    @NotNull(message = "voto spogliatoi è obbligatorio")
    private Double lockerRoom;
    @NotNull(message = "voto servizi è obbligatorio")
    private Double services;
    @NotNull(message = "voto al campo è obbligatorio")
    private Double fieldVote;
    private String note;
    @NotNull(message = "L'id del game è obbligatorio")
    private Long gameMatchId;
    private LocalDate votingDate;
    private LocalTime votingTime;
}
