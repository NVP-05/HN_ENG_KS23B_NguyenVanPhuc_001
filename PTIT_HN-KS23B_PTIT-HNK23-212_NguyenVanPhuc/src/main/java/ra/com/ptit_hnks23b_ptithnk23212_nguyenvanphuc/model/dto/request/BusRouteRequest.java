package ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BusRouteRequest {

    @NotBlank(message = "Điểm bắt đầu không được để trống")
    private String startPoint;

    @NotBlank(message = "Điểm kết thúc không được để trống")
    private String endPoint;

    @NotBlank(message = "Thông tin chuyến đi không được để trống")
    private String tripInformation;

    @NotBlank(message = "Tên lái xe không được để trống")
    private String driverName;

    @NotNull(message = "Trạng thái không được để trống")
    private Boolean status;

    @NotNull(message = "Mã xe buýt không được để trống")
    private Integer busId;
}