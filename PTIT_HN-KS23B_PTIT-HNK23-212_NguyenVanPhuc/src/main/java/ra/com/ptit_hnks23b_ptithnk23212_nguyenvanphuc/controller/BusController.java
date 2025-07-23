package ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.modul.dto.request.BusRequest;
import ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.modul.dto.respone.ApiResponse;
import ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.modul.entity.Bus;
import ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.service.BusService;

import java.util.List;

@RestController
@RequestMapping("/api/buses")
@RequiredArgsConstructor
public class BusController {
    private final BusService busService;
    @GetMapping
    public ResponseEntity<ApiResponse<List<Bus>>> getAllBuses() {
        List<Bus> buses = busService.getAllBuses();
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Danh sách xe buýt", buses, HttpStatus.OK)
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Bus>> addBus(
            @Valid @ModelAttribute BusRequest busRequest,
            @RequestParam("image") MultipartFile imageFile) {

        Bus newBus = busService.addBus(busRequest, imageFile);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Thêm xe buýt thành công", newBus, HttpStatus.CREATED));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Bus>> updateBus(
            @PathVariable Integer id,
            @Valid @ModelAttribute BusRequest busRequest,
            @RequestParam(value = "image", required = false) MultipartFile imageFile) {

        Bus updatedBus = busService.updateBus(id, busRequest, imageFile);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Cập nhật xe buýt thành công", updatedBus, HttpStatus.OK)
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteBus(@PathVariable Integer id) {
        busService.deleteBus(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Xóa xe buýt thành công", null, HttpStatus.OK)
        );
    }
}
