package com.example.CRMforDelivery.service;

import com.example.CRMforDelivery.entity.Courier;
import com.example.CRMforDelivery.entity.dto.CourierDto;
import com.example.CRMforDelivery.entity.dto.CustomerDto;
import com.example.CRMforDelivery.entity.dto.mapper.CourierDtoMapper;
import com.example.CRMforDelivery.repository.CourierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CourierServiceBaseImpl implements CourierService {
    @Autowired
    private final CourierDtoMapper courierDtoMapper;

    @Autowired
    private final CourierRepository courierRepository;

    public CourierServiceBaseImpl(CourierDtoMapper courierDtoMapper,
                                  CourierRepository courierRepository) {
        this.courierDtoMapper = courierDtoMapper;
        this.courierRepository = courierRepository;
    }


    @Override
    public long addCourier(CourierDto courierDto) {
        Courier courier = courierDtoMapper.toEntity(courierDto);
        return courierRepository.save(courier).getId();
    }

    @Override
    public CourierDto getCourierById(Long id) {
        Optional<Courier> optionalCourier = courierRepository.findById(id);
        CourierDto courierDto;
        courierDto = optionalCourier.map(courierDtoMapper::toDto).orElse(null);
        return courierDto;
    }

    @Override
    public boolean deleteCourierById(Long id) {
        if (courierRepository.existsById(id)) {
            courierRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateCourier(Long id, CourierDto courierDto) {
        Courier courierUpdated = courierDtoMapper.toEntity(courierDto);
        Optional<Courier> courierOld = courierRepository.findById(id);
        if (courierOld.isPresent()) {
            courierUpdated.setId(id);
            courierRepository.save(courierUpdated);
            return true;
        } else {
            return false;
        }
    }
}
