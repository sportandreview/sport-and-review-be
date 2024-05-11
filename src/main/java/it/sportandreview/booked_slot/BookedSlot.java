package it.sportandreview.booked_slot;

import it.sportandreview.base.IndexedEntity;
import it.sportandreview.field.Field;
import it.sportandreview.game_match.GameMatch;
import it.sportandreview.slot.Slot;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "app_booked_slot")
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BookedSlot extends IndexedEntity {

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "slot_id", referencedColumnName = "id")
    @ToString.Exclude
    private Slot slot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_id", referencedColumnName = "id")
    @ToString.Exclude
    private Field field;

    @Column(name = "all_day")
    private Boolean allDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_match_id", referencedColumnName = "id")
    @ToString.Exclude
    private GameMatch gameMatch;

}
