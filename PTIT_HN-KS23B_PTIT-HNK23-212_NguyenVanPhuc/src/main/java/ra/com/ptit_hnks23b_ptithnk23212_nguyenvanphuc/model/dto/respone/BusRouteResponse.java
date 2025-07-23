package ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.model.dto.respone;

import lombok.Data;
@Data
public class BusRouteResponse {
    private Integer busRouteId;
    private String startPoint;
    private String endPoint;
    private String tripInformation;
    private String driverName;
    private Boolean status;
    private String busName;
    private String registrationNumber;
}

