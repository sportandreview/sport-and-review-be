package it.sportandreview.field_size;

import it.sportandreview.base.IndexedEntity;
import it.sportandreview.fieal_size_type.FieldSizeType;
import it.sportandreview.field.Field;
import it.sportandreview.sport.Sport;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;


@Entity
@Table(name = "app_field_size")
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class FieldSize extends IndexedEntity {

    @Column(name = "description")
    private String description;

    @Column(name = "dimension")
    private String dimension;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_id", referencedColumnName = "id")
    @ToString.Exclude
    private Sport sport;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_id", referencedColumnName = "id")
    @ToString.Exclude
    private Field field;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_size_type_id", referencedColumnName = "id")
    @ToString.Exclude
    private FieldSizeType fieldSizeType;
}
