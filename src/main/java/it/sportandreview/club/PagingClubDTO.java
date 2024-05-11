package it.sportandreview.club;

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
public class PagingClubDTO {

   private Integer pageSize;
   private List<ClubDTO> clubs = new ArrayList<>();
}
