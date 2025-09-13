package com.example.CRMforDelivery.controller;


import com.example.CRMforDelivery.entity.dto.CourierRequestDto;
import com.example.CRMforDelivery.entity.dto.CourierResponseDto;
import com.example.CRMforDelivery.service.interfaces.CourierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/couriers")
@RequiredArgsConstructor
public class CourierController {

    private final CourierService courierService;

    @GetMapping("/{id}")
    public ResponseEntity<CourierResponseDto> getCourier(@PathVariable Long id) {
        CourierResponseDto courierDto = courierService.getCourierById(id);
        HttpStatus status;
        if (courierDto == null) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.OK;
        }
        return ResponseEntity
                .status(status)
                .body(courierDto);
    }

    @GetMapping("/exists/{tgUserName}")
    public ResponseEntity<Boolean> existsByTgUsername(@PathVariable String tgUserName) {
        boolean isExists = courierService.findByTgUserName(tgUserName);
        return ResponseEntity
                .ok()
                .body(isExists);
    }

    @PostMapping()
    public ResponseEntity<String> addCourier(@Valid @RequestBody CourierRequestDto courierDto) {
        long id = courierService.addCourier(courierDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, "/api/courier/" + id)
                .body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> deleteCourier(@Valid @RequestBody CourierRequestDto courierDto,
                                           @PathVariable Long id) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        if (courierService.updateCourier(id, courierDto)) {
            status = HttpStatus.NO_CONTENT;
        }
        return ResponseEntity
                .status(status)
                .body(null);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourier(@PathVariable Long id) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        if (courierService.deleteCourierById(id)) {
            status = HttpStatus.NO_CONTENT;
        }
        return ResponseEntity
                .status(status)
                .body(null);
    }


}
