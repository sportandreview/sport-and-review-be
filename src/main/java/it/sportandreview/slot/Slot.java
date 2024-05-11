package it.sportandreview.slot;

import it.sportandreview.base.IndexedEntity;
import it.sportandreview.slot_planning.SlotPlanning;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalTime;


@Entity
@Table(name = "app_slot")
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Slot extends IndexedEntity {

    @Column(name = "time")
    private LocalTime time;

    @Column(name = "duration_slot")
    private LocalTime durationSlot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planning_id", referencedColumnName = "id")
    @ToString.Exclude
    private SlotPlanning planning;
}
