package it.sportandreview.field_size;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldSizeRepository extends JpaRepository<FieldSize, Long> {

}
