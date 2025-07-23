package ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.model.entity.Bus;

import java.util.Optional;

public interface BusRepository extends JpaRepository<ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.model.entity.Bus, Integer> {

    boolean existsByBusName(String busName);

    boolean existsByRegistrationNumber(String registrationNumber);

    Optional<Bus> findByBusName(String busName);

    Optional<Bus> findByRegistrationNumber(String registrationNumber);
}
