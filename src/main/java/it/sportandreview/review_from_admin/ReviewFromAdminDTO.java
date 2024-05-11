package it.sportandreview.review_from_admin;

import it.sportandreview.base.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class ReviewFromAdminDTO extends BaseDTO {

    private Long playerUserId;
    private Long adminUserId;
    private Long gameMatchId;
    private LocalDateTime dateAndTimeReview;
    private Double booking;
    private Double behaviorRace;
}
