package it.sportandreview.opening_day;

import it.sportandreview.base.IndexedEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Entity
@Table(name = "app_opening_day")
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class OpeningDay extends IndexedEntity {

    @Column(name = "description")
    private String description;

    @Column(name = "opening_time")
    private ZonedDateTime openingTime;

    @Column(name = "closing_time")
    private ZonedDateTime closingTime;
}
