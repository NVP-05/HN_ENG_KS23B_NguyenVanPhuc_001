package ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.exception.DuplicateNameException;
import ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.exception.ResourceNotFoundException;
import ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.model.dto.request.BusRequest;
import ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.model.dto.respone.BusResponse;
import ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.model.entity.Bus;
import ra.com.ptit_hnks23b_ptithnk23212_nguyenvanphuc.repository.BusRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BusService {
    private final BusRepository busRepository;

    // ✅ Get all buses (return DTO)
    public List<BusResponse> getAllBuses() {
        return busRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // ✅ Add bus (return DTO)
    public BusResponse addBus(BusRequest request) {
        validateBusNameAndRegistration(request.getBusName(), request.getRegistrationNumber(), null);

        Bus bus = new Bus();
        BeanUtils.copyProperties(request, bus);
        bus.setStatus(true);

        Bus savedBus = busRepository.save(bus);
        return convertToResponse(savedBus);
    }

    // ✅ Update bus (return DTO)
    public BusResponse updateBus(Integer busId, BusRequest request, MultipartFile imageFile) {
        Bus existingBus = busRepository.findById(busId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy xe buýt với ID: " + busId));

        validateBusNameAndRegistration(request.getBusName(), request.getRegistrationNumber(), busId);

        BeanUtils.copyProperties(request, existingBus);

        Bus updatedBus = busRepository.save(existingBus);
        return convertToResponse(updatedBus);
    }

    // ✅ Delete bus (soft delete)
    public void deleteBus(Integer busId) {
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy xe buýt với ID: " + busId));

        bus.setStatus(false);
        busRepository.save(bus);
    }

    // ✅ Helper: Convert Entity -> DTO
    private BusResponse convertToResponse(Bus bus) {
        BusResponse response = new BusResponse();
        BeanUtils.copyProperties(bus, response);
        return response;
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
