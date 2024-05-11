package it.sportandreview.club_review;

import it.sportandreview.base.BaseDTO;
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

    private Long playerUserId;
    private Long clubId;
    private Double customerServices;
    private Double lockerRoom;
    private Double services;
    private String note;
    private Long gameMatchId;
    private LocalDate votingDate;
    private LocalTime votingTime;
}
