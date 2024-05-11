package it.sportandreview.fieal_size_type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FieldSizeTypeRepository extends JpaRepository<FieldSizeType, Long> {

}
