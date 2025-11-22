package com.example.CRMforDelivery.controller;

import com.example.CRMforDelivery.config.MessageSourceConfig;
import com.example.CRMforDelivery.controller.exceptionHandler.RestExceptionHandler;
import com.example.CRMforDelivery.entity.dto.CourierResponseDto;
import com.example.CRMforDelivery.exceptions.NoSuchCourierException;
import com.example.CRMforDelivery.service.interfaces.CourierService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Locale;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CourierController.class)
@Import(MessageSourceConfig.class)
class CourierControllerTest {

    @MockitoBean
    private CourierService courierService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestExceptionHandler restExceptionHandler;

    @Autowired
    private MessageSource messageSource;

    @Nested
    @DisplayName("Tecты get")
    class GetTest {

        @Test
        @WithMockUser
        @DisplayName("существующий id")
        void getCourier_existedId_courierDto() throws Exception {

            var expectedCourierResponseDto = new CourierResponseDto(
                    "testUser",
                    "userName",
                    "userSurname",
                    "userLastname",
                    "+37529-888-88-88");
            when(courierService.getCourierById(1L)).
                    thenReturn(ResponseEntity.ok(expectedCourierResponseDto));

            mockMvc.perform(get("/api/couriers/1"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(content().json(
                            objectMapper.writeValueAsString(expectedCourierResponseDto)));


        }

        @Test
        @WithMockUser
        @DisplayName("несуществующий id")
        void getCourier_notExistedId_errorMessage() throws Exception {

            var message = messageSource
                    .getMessage("courier.not.found", new Object[]{1L}, Locale.getDefault());
            var exception = new NoSuchCourierException(message);
            when(courierService.getCourierById(1L)).
                    thenThrow(exception);

            mockMvc.perform(get("/api/couriers/1"))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                    .andExpect(content().string(exception.getMessage()));

        }

        @Test
        @WithMockUser
        @DisplayName("существующий tgUsername")
        void existsByTgUsername_existedTgUsername_true() throws Exception{

            when(courierService.findByTgUserName("tgUsername"))
                    .thenReturn(ResponseEntity
                            .ok()
                            .body(true)
                    );

            mockMvc.perform(get("/api/couriers/exists/tgUsername"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("true"));

        }

        @Test
        @WithMockUser
        @DisplayName("несуществующий tgUsername")
        void existsByTgUsername_notExistedTgUsername_true() throws Exception{

            when(courierService.findByTgUserName("tgUsername"))
                    .thenReturn(ResponseEntity
                            .ok()
                            .body(false)
                    );

            mockMvc.perform(get("/api/couriers/exists/tgUsername"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("false"));

        }


    }




}
