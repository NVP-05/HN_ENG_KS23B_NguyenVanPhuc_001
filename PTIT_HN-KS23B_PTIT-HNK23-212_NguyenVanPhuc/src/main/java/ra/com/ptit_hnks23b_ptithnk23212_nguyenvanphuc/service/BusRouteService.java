package ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.service;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.exception.ResourceNotFoundException;
import ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.modul.dto.request.BusRouteRequest;
import ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.modul.entity.Bus;
import ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.modul.entity.BusRoute;
import ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.repository.BusRepository;
import ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.repository.BusRouteRepository;
import java.util.List;

@Service
public class BusRouteService {

    @Autowired
    private BusRouteRepository busRouteRepository;

    @Autowired
    private BusRepository busRepository;

    public List<BusRoute> getAllRoutesSorted() {
        return busRouteRepository.findAll(Sort.by("startPoint").ascending());
    }

    public BusRoute addRoute(@Valid BusRouteRequest dto) {
        Bus buses = busRepository.findById(dto.getBusId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy xe buýt với ID: " + dto.getBusId()));

        BusRoute route = new BusRoute();
        BeanUtils.copyProperties(dto, route);
        route.setBuses(buses);
        route.setStatus(true);
        return busRouteRepository.save(route);
    }

    public BusRoute updateRoute(Integer id, @Valid BusRouteRequest dto) {
        BusRoute route = busRouteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy chuyến đi với ID: " + id));

        route.setStartPoint(dto.getStartPoint());
        route.setEndPoint(dto.getEndPoint());
        route.setTripInformation(dto.getTripInformation());
        route.setDriverName(dto.getDriverName());
        route.setStatus(dto.getStatus());

        return busRouteRepository.save(route);
    }

    public void deleteRoute(Integer id) {
        BusRoute route = busRouteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy chuyến đi với ID: " + id));
        route.setStatus(false);
        busRouteRepository.save(route);
    }

}
