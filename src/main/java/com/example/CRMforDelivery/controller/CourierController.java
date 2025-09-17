package com.example.CRMforDelivery.controller;


import com.example.CRMforDelivery.entity.dto.CourierRequestDto;
import com.example.CRMforDelivery.entity.dto.CourierResponseDto;
import com.example.CRMforDelivery.service.interfaces.CourierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/couriers")
@RequiredArgsConstructor
public class CourierController {

    private final CourierService courierService;

    @GetMapping("/{id}")
    public ResponseEntity<CourierResponseDto> getCourier(@PathVariable Long id) {
        return courierService.getCourierById(id);
    }

    @GetMapping("/exists/{tgUserName}")
    public ResponseEntity<Boolean> existsByTgUsername(@PathVariable String tgUserName) {
        return courierService.findByTgUserName(tgUserName);
    }

    @PostMapping()
    public ResponseEntity<?> addCourier(@Valid @RequestBody CourierRequestDto courierDto) {
        return courierService.addCourier(courierDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourier(@Valid @RequestBody CourierRequestDto courierDto,
                                           @PathVariable Long id) {
        return courierService.updateCourier(id, courierDto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourier(@PathVariable Long id) {
        return courierService.deleteCourierById(id);
    }


}
