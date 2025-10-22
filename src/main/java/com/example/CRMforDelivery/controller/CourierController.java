package com.example.CRMforDelivery.controller;


import com.example.CRMforDelivery.entity.dto.CourierRequestDto;
import com.example.CRMforDelivery.entity.dto.CourierResponseDto;
import com.example.CRMforDelivery.service.interfaces.CourierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("${api.couriers.base}")
@Tag(name = "CourierController", description = "Операции для курьеров")
@RequiredArgsConstructor
public class CourierController {

    private final CourierService courierService;

    @Operation(
            summary = "Получение курьера",
            description = "Получение данных о Курьере по id")
    @GetMapping("/{id}")
    public ResponseEntity<CourierResponseDto> getCourier(
            @PathVariable @Parameter(description = "id курьера") Long id) {
        return courierService.getCourierById(id);
    }

    @Operation(
            summary = "Получение tg имени курьера",
            description = "Получение username курьера из Telegram")
    @GetMapping("/exists/{tgUserName}")
    public ResponseEntity<Boolean> existsByTgUsername(
            @PathVariable @Parameter(description = "username из Telegram курьера") String tgUserName) {
        return courierService.findByTgUserName(tgUserName);
    }

    @Operation(
            summary = "Добавление курьера",
            description = "Добавление нового курьера")
    @PostMapping()
    public ResponseEntity<?> addCourier(@Valid @RequestBody CourierRequestDto courierDto) {
        return courierService.addCourier(courierDto);
    }

    @Operation(
            summary = "Обновление курьера",
            description = "Обновление данных курьера по ешо id")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourier(
            @Valid @RequestBody CourierRequestDto courierDto,
            @PathVariable @Parameter(description = "id курьера") Long id) {
        return courierService.updateCourier(id, courierDto);
    }

    @Operation(
            summary = "Удаление курьера",
            description = "Удаление курьера по его id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourier(
            @PathVariable @Parameter(description = "id курьера") Long id) {
        return courierService.deleteCourierById(id);
    }

}
