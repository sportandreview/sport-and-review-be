package it.sportandreview.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@EqualsAndHashCode
@Data
@NoArgsConstructor
public abstract class BaseDTO {

    protected Long id;

    protected String uuid;

    protected BaseDTO(Long id, String uuid) {
        this.id = id;
        this.uuid = uuid;
    }
}
