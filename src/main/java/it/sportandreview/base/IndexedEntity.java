package it.sportandreview.base;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@ToString
@SuperBuilder(toBuilder = true)
public abstract class IndexedEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 9202158428802652845L;

    @Id
    @Column(name = "id")
    @EqualsAndHashCode.Exclude
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String uuid;

    protected IndexedEntity(Long id, String uuid) {
        this.id = id;
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof IndexedEntity other))
            return false;

        // if the id is missing, return false
        if (Objects.isNull(id)) return false;

        // equivalence by id
        return id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
