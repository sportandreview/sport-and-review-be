package it.sportandreview.field;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {

    List<Field> findByClubId(Long ClubId);
    List<Field> findTop5ByClubIdOrderByRatingDesc(long clubId);
}
