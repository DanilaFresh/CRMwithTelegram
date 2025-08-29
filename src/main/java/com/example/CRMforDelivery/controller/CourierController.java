package com.example.CRMforDelivery.controller;


import com.example.CRMforDelivery.entity.Courier;
import com.example.CRMforDelivery.entity.dto.CourierDto;
import com.example.CRMforDelivery.entity.dto.CustomerDto;
import com.example.CRMforDelivery.service.CourierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/courier")
public class CourierController {

    @Autowired
    private final CourierService courierService;

    public CourierController(CourierService courierService) {
        this.courierService = courierService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourierDto> getCourier(@PathVariable Long id) {
        CourierDto courierDto = courierService.getCourierById(id);
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

    @PostMapping()
    public ResponseEntity<String> addCourier(@Valid @RequestBody CourierDto courierDto) {
        long id = courierService.addCourier(courierDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, "/api/courier/" + id)
                .body(null);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> deleteCourier(@Valid @RequestBody CourierDto courierDto,
                                            @PathVariable Long id) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        if (courierService.updateCourier(id,courierDto)) {
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
