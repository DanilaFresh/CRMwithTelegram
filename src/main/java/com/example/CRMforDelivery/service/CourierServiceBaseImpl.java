package com.example.CRMforDelivery.service;

import com.example.CRMforDelivery.entity.Courier;
import com.example.CRMforDelivery.entity.dto.CourierRequestDto;
import com.example.CRMforDelivery.entity.dto.CourierResponseDto;
import com.example.CRMforDelivery.entity.dto.mapper.CourierDtoMapper;
import com.example.CRMforDelivery.exceptions.NoSuchCourierException;
import com.example.CRMforDelivery.repository.CourierRepository;
import com.example.CRMforDelivery.repository.UserAuthorityRepository;
import com.example.CRMforDelivery.repository.UserRepository;
import com.example.CRMforDelivery.service.interfaces.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CourierServiceBaseImpl implements CourierService {

    private final CourierDtoMapper courierDtoMapper;

    private final CourierRepository courierRepository;

    private final MessageSource messageSource;

    @Override
    public ResponseEntity<CourierResponseDto> getCourierById(Long id) {
        Optional<Courier> optionalCourier = courierRepository.findById(id);
        CourierResponseDto courierDto;
        courierDto = optionalCourier
                .map(courierDtoMapper::toResponseDto)
                .orElseThrow(() -> {
                    var message = messageSource
                            .getMessage("courier.not.found", new Object[]{id}, Locale.getDefault());
                    return new NoSuchCourierException(message);
                });
        return ResponseEntity
                .ok()
                .body(courierDto);
    }

    @Override
    public ResponseEntity<Boolean> findByTgUserName(String username) {
        return ResponseEntity
                .ok()
                .body(courierRepository.findByTgUserName(username).isPresent());
    }

    @Override
    public ResponseEntity<?> addCourier(CourierRequestDto courierDto) {
        Courier courier = courierDtoMapper.toEntity(courierDto);
        long id = courierRepository.save(courier).getId();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, "/api/couriers/" + id)
                .body(null);
    }

    @Override
    public ResponseEntity<?> updateCourier(Long id, CourierRequestDto courierDto) {
        Courier courierUpdated = courierDtoMapper.toEntity(courierDto);
        Optional<Courier> courierOld = courierRepository.findById(id);
        courierOld.orElseThrow(() -> {
            var message = messageSource
                    .getMessage("courier.not.found", new Object[]{id}, Locale.getDefault());
            return new NoSuchCourierException(message);
        });
        courierUpdated.setId(id);
        courierRepository.save(courierUpdated);
        return ResponseEntity
                .noContent()
                .build();
    }


    @Override
    public ResponseEntity<?> deleteCourierById(Long id) {
        if (!courierRepository.existsById(id)) {
            var message = messageSource
                    .getMessage("courier.not.found", new Object[]{id},  Locale.getDefault());
            throw new NoSuchCourierException(message);
        }
        courierRepository.deleteById(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}