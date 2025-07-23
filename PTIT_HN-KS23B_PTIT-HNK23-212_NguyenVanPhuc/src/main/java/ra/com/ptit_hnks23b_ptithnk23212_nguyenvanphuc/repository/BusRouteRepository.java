package ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.model.entity.BusRoute;

import java.util.List;

public interface BusRouteRepository extends JpaRepository<BusRoute, Integer> {
    List<BusRoute> findByStartPointContainingIgnoreCaseAndEndPointContainingIgnoreCase(
            String startPoint, String endPoint
    );
}
