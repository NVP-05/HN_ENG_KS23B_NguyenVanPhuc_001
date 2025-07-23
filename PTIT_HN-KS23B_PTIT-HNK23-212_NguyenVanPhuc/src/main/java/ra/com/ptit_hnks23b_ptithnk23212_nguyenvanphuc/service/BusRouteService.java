package ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.service;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.exception.ResourceNotFoundException;
import ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.model.dto.request.BusRouteRequest;
import ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.model.dto.respone.BusRouteResponse;
import ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.model.entity.Bus;
import ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.model.entity.BusRoute;
import ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.repository.BusRepository;
import ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.repository.BusRouteRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusRouteService {

    @Autowired
    private BusRouteRepository busRouteRepository;

    @Autowired
    private BusRepository busRepository;

    public List<BusRouteResponse> getAllRoutesSorted() {
        return busRouteRepository.findAll(Sort.by("startPoint").ascending())
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public BusRouteResponse addRoute(@Valid BusRouteRequest dto) {
        Bus bus = busRepository.findById(dto.getBusId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy xe buýt với ID: " + dto.getBusId()));

        BusRoute route = new BusRoute();
        BeanUtils.copyProperties(dto, route);
        route.setBus(bus);
        route.setStatus(true);
        BusRoute savedRoute = busRouteRepository.save(route);

        return convertToResponse(savedRoute);
    }

    public BusRouteResponse updateRoute(Integer id, @Valid BusRouteRequest dto) {
        BusRoute route = busRouteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy chuyến đi với ID: " + id));

        BeanUtils.copyProperties(dto, route, "busRouteId", "bus");
        route.setStatus(dto.getStatus());

        BusRoute updatedRoute = busRouteRepository.save(route);
        return convertToResponse(updatedRoute);
    }

    public void deleteRoute(Integer id) {
        BusRoute route = busRouteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy chuyến đi với ID: " + id));
        route.setStatus(false);
        busRouteRepository.save(route);
    }
    private BusRouteResponse convertToResponse(BusRoute route) {
        BusRouteResponse response = new BusRouteResponse();
        response.setBusRouteId(route.getBusRouteId());
        response.setStartPoint(route.getStartPoint());
        response.setEndPoint(route.getEndPoint());
        response.setTripInformation(route.getTripInformation());
        response.setDriverName(route.getDriverName());
        response.setStatus(route.getStatus());
        response.setBusName(route.getBus().getBusName());
        response.setRegistrationNumber(route.getBus().getRegistrationNumber());
        return response;
    }
}
