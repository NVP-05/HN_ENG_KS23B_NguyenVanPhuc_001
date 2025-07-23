package ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.modul.dto.request.BusRouteRequest;
import ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.modul.dto.respone.ApiResponse;
import ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.modul.entity.BusRoute;
import ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.service.BusRouteService;

import java.util.List;

@RestController
@RequestMapping("/api/bus-routes")
@RequiredArgsConstructor
public class BusRouteController {

    private final BusRouteService busRouteService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<BusRoute>>> getAllBusRoutes() {
        List<BusRoute> routes = busRouteService.getAllRoutesSorted();
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Danh sách chuyến đi", routes, HttpStatus.OK)
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BusRoute>> addRoute(@Valid @RequestBody BusRouteRequest routeRequest) {
        BusRoute newRoute = busRouteService.addRoute(routeRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Thêm chuyến đi thành công", newRoute, HttpStatus.CREATED));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BusRoute>> updateRoute(
            @PathVariable Integer id,
            @Valid @RequestBody BusRouteRequest routeRequest) {

        BusRoute updatedRoute = busRouteService.updateRoute(id, routeRequest);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Cập nhật chuyến đi thành công", updatedRoute, HttpStatus.OK)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteRoute(@PathVariable Integer id) {
        busRouteService.deleteRoute(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Xóa chuyến đi thành công", null, HttpStatus.OK)
        );
    }

}