package ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.modul.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "buses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bus_id")
    private Integer busId;

    @Column(name = "bus_name", unique = true, nullable = false, length = 100)
    private String busName;

    @Column(name = "registration_number", unique = true, nullable = false, length = 30)
    private String registrationNumber;

    @Column(name = "total_seats", nullable = false)
    private Integer totalSeats;

    @Column(name = "image_bus")
    private String imageBus;

    @Column(name = "status", nullable = false, columnDefinition = "bit default 1")
    private Boolean status = true;

    @OneToMany(mappedBy = "buses", cascade = CascadeType.ALL)
    private List<BusRoute> busRoutes;
}

