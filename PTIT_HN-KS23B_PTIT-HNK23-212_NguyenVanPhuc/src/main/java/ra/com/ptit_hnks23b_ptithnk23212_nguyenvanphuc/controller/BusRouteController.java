package ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.model.dto.request.BusRouteRequest;
import ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.model.dto.respone.ApiResponse;
import ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.model.dto.respone.BusRouteResponse;
import ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.service.BusRouteService;

import java.util.List;

@RestController
@RequestMapping("/api/bus-routes")
@RequiredArgsConstructor
public class BusRouteController {

    private final BusRouteService busRouteService;

    // ✅ GET all bus routes
    @GetMapping
    public ResponseEntity<ApiResponse<List<BusRouteResponse>>> getAllBusRoutes() {
        List<BusRouteResponse> routes = busRouteService.getAllRoutesSorted();
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Danh sách chuyến đi", routes, HttpStatus.OK)
        );
    }

    // ✅ POST add new bus route
    @PostMapping
    public ResponseEntity<ApiResponse<BusRouteResponse>> addRoute(
            @Valid @RequestBody BusRouteRequest routeRequest) {

        BusRouteResponse newRoute = busRouteService.addRoute(routeRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Thêm chuyến đi thành công", newRoute, HttpStatus.CREATED));
    }

    // ✅ PUT update bus route
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BusRouteResponse>> updateRoute(
            @PathVariable Integer id,
            @Valid @RequestBody BusRouteRequest routeRequest) {

        BusRouteResponse updatedRoute = busRouteService.updateRoute(id, routeRequest);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Cập nhật chuyến đi thành công", updatedRoute, HttpStatus.OK)
        );
    }

    // ✅ DELETE (soft delete) bus route
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteRoute(@PathVariable Integer id) {
        busRouteService.deleteRoute(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Xóa chuyến đi thành công", null, HttpStatus.OK)
        );
    }
}
