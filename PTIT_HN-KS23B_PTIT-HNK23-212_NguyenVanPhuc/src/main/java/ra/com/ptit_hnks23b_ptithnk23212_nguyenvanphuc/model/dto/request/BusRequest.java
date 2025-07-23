package ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BusRequest {

    @NotBlank(message = "Tên xe buýt không được để trống")
    private String busName;

    @NotBlank(message = "Số đăng ký xe không được để trống")
    private String registrationNumber;

    @NotNull(message = "Tổng số ghế không được để trống")
    @Min(value = 1, message = "Tổng số ghế phải lớn hơn 0")
    private Integer totalSeats;

    private Boolean status;
}
