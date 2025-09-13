package com.example.CRMforDelivery.service;

import com.example.CRMforDelivery.entity.Courier;
import com.example.CRMforDelivery.entity.User;
import com.example.CRMforDelivery.entity.UserAuthority;
import com.example.CRMforDelivery.entity.dto.CourierRequestDto;
import com.example.CRMforDelivery.entity.dto.CourierResponseDto;
import com.example.CRMforDelivery.entity.dto.mapper.CourierDtoMapper;
import com.example.CRMforDelivery.repository.CourierRepository;
import com.example.CRMforDelivery.repository.UserAuthorityRepository;
import com.example.CRMforDelivery.repository.UserRepository;
import com.example.CRMforDelivery.service.interfaces.CourierService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CourierServiceBaseImpl implements CourierService {

    private final CourierDtoMapper courierDtoMapper;

    private final CourierRepository courierRepository;

    public CourierServiceBaseImpl(UserRepository userRepository, UserAuthorityRepository authorityRepository, CourierDtoMapper courierDtoMapper,
                                  CourierRepository courierRepository) {
        this.courierDtoMapper = courierDtoMapper;
        this.courierRepository = courierRepository;
    }


    @Override
    public long addCourier(CourierRequestDto courierDto) {
        Courier courier = courierDtoMapper.toEntity(courierDto);
        return courierRepository.save(courier).getId();
    }

    @Override
    public CourierResponseDto getCourierById(Long id) {
        Optional<Courier> optionalCourier = courierRepository.findById(id);
        CourierResponseDto courierDto;
        courierDto = optionalCourier.map(courierDtoMapper::toResponseDto).orElse(null);
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
    public boolean updateCourier(Long id, CourierRequestDto courierDto) {
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

    @Override
    public boolean findByTgUserName(String username) {
        return courierRepository.findByTgUserName(username).isPresent();
    }
}
