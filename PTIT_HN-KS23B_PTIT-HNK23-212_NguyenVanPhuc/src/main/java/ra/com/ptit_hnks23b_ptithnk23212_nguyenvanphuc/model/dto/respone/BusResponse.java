package ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.model.dto.respone;

import lombok.Data;

@Data
public class BusResponse {
    private Integer busId;
    private String busName;
    private String registrationNumber;
    private Integer totalSeats;
    private String imageBus;
    private Boolean status;
}

