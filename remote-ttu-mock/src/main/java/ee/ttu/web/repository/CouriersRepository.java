package ee.ttu.web.repository;

import ee.ttu.web.domain.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouriersRepository extends JpaRepository<Courier, Long> {
}
