package it.sportandreview.field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PagingFieldDTO {

    private Integer pageSize;
    private List<FieldDTO> fields = new ArrayList<>();

}
