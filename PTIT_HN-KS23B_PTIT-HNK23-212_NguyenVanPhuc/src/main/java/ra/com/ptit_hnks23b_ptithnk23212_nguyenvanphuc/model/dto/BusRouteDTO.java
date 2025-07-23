package ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.model.dto;

import lombok.Data;

@Data
public class BusRouteDTO {
    private Integer busRouteId;
    private String startPoint;
    private String endPoint;
    private String tripInformation;
    private String driverName;
    private Boolean status;
    private Integer busId;
    private String busName;
}
