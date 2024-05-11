package it.sportandreview.slot_planning;

import it.sportandreview.base.IndexedEntity;
import it.sportandreview.field.Field;
import it.sportandreview.slot.Slot;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "app_slot_planning")
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SlotPlanning extends IndexedEntity {

    @Column(name = "days_of_week")
    private String daysOfWeek;

    @Column(name = "duration_slot")
    private LocalTime durationSlot;

    @Column(name = "opening_time")
    private LocalTime openingTime;

    @Column(name = "closing_time")
    private LocalTime closingTime;

    @Column(name = "start_date_validity")
    private LocalDate startDateValidity;

    @Column(name = "end_date_validity")
    private LocalDate endDateValidity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_id", referencedColumnName = "id")
    @ToString.Exclude
    private Field field;

    @OneToMany(mappedBy = "planning")
    @Builder.Default
    private Set<Slot> slots = new HashSet<>();

}
