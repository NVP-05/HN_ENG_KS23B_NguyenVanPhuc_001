package ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.exception.DuplicateNameException;
import ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.exception.ResourceNotFoundException;
import ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.modul.dto.request.BusRequest;
import ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.modul.entity.Bus;
import ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.repository.BusRepository;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BusService {
    private final BusRepository busRepository;
    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }
    public Bus addBus(BusRequest request, MultipartFile imageFile) {
        validateBusNameAndRegistration(request.getBusName(), request.getRegistrationNumber(), null);

        Bus bus = new Bus();
        BeanUtils.copyProperties(request, bus);
        bus.setStatus(true);
        return busRepository.save(bus);
    }
    public Bus updateBus(Integer busId, BusRequest request, MultipartFile imageFile) {
        Bus existingBus = busRepository.findById(busId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy xe buýt với ID: " + busId));

        validateBusNameAndRegistration(request.getBusName(), request.getRegistrationNumber(), busId);

        BeanUtils.copyProperties(request, existingBus);

        return busRepository.save(existingBus);
    }

    public void deleteBus(Integer busId) {
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy xe buýt với ID: " + busId));

        if (!bus.getBusRoutes().isEmpty()) {
            throw new IllegalStateException("Không thể xóa xe buýt vì đang có chuyến đi.");
        }

        bus.setStatus(false);
        busRepository.save(bus);
    }

    private void validateBusNameAndRegistration(String busName, String registrationNumber, Integer excludeId) {
        Optional<Bus> busWithSameName = busRepository.findByBusName(busName);
        if (busWithSameName.isPresent() && !busWithSameName.get().getBusId().equals(excludeId)) {
            throw new DuplicateNameException("Tên xe buýt đã tồn tại: " + busName);
        }

        Optional<Bus> busWithSameReg = busRepository.findByRegistrationNumber(registrationNumber);
        if (busWithSameReg.isPresent() && !busWithSameReg.get().getBusId().equals(excludeId)) {
            throw new DuplicateNameException("Số đăng ký xe đã tồn tại: " + registrationNumber);
        }
    }

}