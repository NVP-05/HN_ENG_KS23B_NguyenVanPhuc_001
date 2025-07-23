package ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BusDTO {
    @NotBlank(message = "Bus name is required")
    private String busName;

    @NotBlank(message = "Registration number is required")
    private String registrationNumber;

    @NotNull(message = "Total seats is required")
    @Min(1)
    private Integer totalSeats;

    private Boolean status;
}
