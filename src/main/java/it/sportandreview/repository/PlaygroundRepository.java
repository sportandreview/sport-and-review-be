package it.sportandreview.repository;

import it.sportandreview.entity.Playground;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaygroundRepository extends JpaRepository<Playground, Long> {
}
