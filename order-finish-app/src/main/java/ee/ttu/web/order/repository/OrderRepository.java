package ee.ttu.web.order.repository;

import ee.ttu.web.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT order FROM Order order WHERE order.trackingNumber = :trackingId")
    Order findOneByTrackingId(@Param("trackingId") String trackingId);
}
