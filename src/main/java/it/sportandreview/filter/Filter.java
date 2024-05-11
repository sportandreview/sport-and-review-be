package it.sportandreview.filter;

import it.sportandreview.base.IndexedEntity;
import it.sportandreview.club.Club;
import it.sportandreview.field.Field;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;



@Entity
@Table(name = "app_filter")
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Filter extends IndexedEntity {

    @Column(name = "filter_name")
    private String filterName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", referencedColumnName = "id")
    @ToString.Exclude
    private Club club;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_id", referencedColumnName = "id")
    @ToString.Exclude
    private Field field;

}
