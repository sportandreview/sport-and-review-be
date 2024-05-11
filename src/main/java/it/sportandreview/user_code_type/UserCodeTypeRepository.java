package it.sportandreview.user_code_type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCodeTypeRepository extends JpaRepository<UserCodeType, Long> {

}
