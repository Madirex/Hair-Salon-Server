package com.madirex.hairsalonserver.controllers.service;

import com.madirex.hairsalonserver.controller.ServiceController;
import com.madirex.hairsalonserver.dto.service.ServiceDTO;
import com.madirex.hairsalonserver.exceptions.ServiceNotFoundException;
import com.madirex.hairsalonserver.exceptions.service.ServiceBadRequestException;
import com.madirex.hairsalonserver.mapper.ServiceMapper;
import com.madirex.hairsalonserver.model.Service;
import com.madirex.hairsalonserver.repository.ServiceRepository;
import com.madirex.hairsalonserver.services.uploads.StorageService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ServiceControllerMockTest {
    @MockBean
    private final ServiceRepository serviceRepository;
    @MockBean
    private final StorageService storageService;
    @MockBean
    private final ServiceMapper serviceMapper;
    private final Service service = Service.builder()
            .id("ec272c62-9d31-11ec-b909-0242ac120002")
            .name("nombre")
            .price(2.0)
            .stock(2)
            .build();
    @InjectMocks
    private ServiceController serviceController;

    @Autowired
    public ServiceControllerMockTest(ServiceRepository serviceRepository, StorageService storageService, ServiceMapper serviceMapper) {
        this.serviceRepository = serviceRepository;
        this.storageService = storageService;
        this.serviceMapper = serviceMapper;
    }

    @Test
    @Order(1)
    void getAllTestMock() {
        ServiceDTO serviceDTO = ServiceDTO.builder()
                .name(service.getName())
                .price(service.getPrice())
                .stock(service.getStock())
                .build();

        Mockito.when(serviceRepository.findAll())
                .thenReturn(List.of(service));

        Mockito.when(serviceMapper.toDTO(List.of(service))).thenReturn(List.of(serviceDTO));

        var response = serviceController.findAll(
                java.util.Optional.empty()
        );
        var res = response.getBody();

        assertAll(
                () -> assertEquals(HttpStatus.OK.value(), response.getStatusCode().value()),
                () -> Assertions.assertEquals(res.get(0).getName(), service.getName()),
                () -> Assertions.assertEquals(res.get(0).getPrice(), service.getPrice()),
                () -> Assertions.assertEquals(res.get(0).getStock(), service.getStock())
        );
    }

    @Test
    @Order(2)
    void getByIdTestMock() {
        ServiceDTO serviceDTO = ServiceDTO.builder()
                .name(service.getName())
                .price(service.getPrice())
                .stock(service.getStock())
                .build();

        Mockito.when(serviceRepository.findById(service.getId()))
                .thenReturn(Optional.of(service));

        Mockito.when(serviceMapper.toDTO(service)).thenReturn(serviceDTO);

        var response = serviceController.findById(service.getId());
        var res = response.getBody();

        assertAll(
                () -> assertEquals(HttpStatus.OK.value(), response.getStatusCode().value()),
                () -> Assertions.assertEquals(res.getName(), service.getName()),
                () -> Assertions.assertEquals(res.getPrice(), service.getPrice()),
                () -> Assertions.assertEquals(res.getStock(), service.getStock())
        );

        Mockito.verify(serviceRepository, Mockito.times(1)).findById(service.getId());
        Mockito.verify(serviceMapper, Mockito.times(1)).toDTO(service);
    }

    @Test
    @Order(3)
    void findByIdException() {
        Mockito.when(serviceRepository.findById(service.getId())).thenReturn(Optional.empty());

        Exception ex = assertThrows(ServiceNotFoundException.class, () -> {
            serviceController.findById(service.getId());
        });

        assertTrue(ex.getMessage().contains("service"));

        Mockito.verify(serviceRepository, Mockito.times(1))
                .findById(service.getId());
    }

    @Test
    @Order(4)
    void saveTestMock() {
        ServiceDTO serviceCreateDTO = ServiceDTO.builder()
                .name(service.getName())
                .price(service.getPrice())
                .stock(service.getStock())
                .build();

        ServiceDTO serviceDTO = ServiceDTO.builder()
                .name(service.getName())
                .price(service.getPrice())
                .stock(service.getStock())
                .build();

        Mockito.when(serviceRepository.save(service))
                .thenReturn(service);

        Mockito.when(serviceMapper.fromDTO(serviceCreateDTO))
                .thenReturn(service);

        Mockito.when(serviceMapper.toDTO(service)).thenReturn(serviceDTO);

        var response = serviceController.newService(serviceCreateDTO);
        var res = response.getBody();

        assertAll(
                () -> assertEquals(HttpStatus.OK.value(), response.getStatusCode().value()),
                () -> Assertions.assertEquals(res.getName(), service.getName()),
                () -> Assertions.assertEquals(res.getPrice(), service.getPrice()),
                () -> Assertions.assertEquals(res.getStock(), service.getStock())
        );

        Mockito.verify(serviceRepository, Mockito.times(1))
                .save(service);
        Mockito.verify(serviceMapper, Mockito.times(1))
                .fromDTO(serviceCreateDTO);
        Mockito.verify(serviceMapper, Mockito.times(1))
                .toDTO(service);
    }

    @Test
    @Order(5)
    void checkServiceDataNameExceptionTest() {
        ServiceDTO serviceCreateDTO = ServiceDTO.builder()
                .name(service.getName())
                .price(service.getPrice())
                .stock(service.getStock())
                .build();

        Service serv = Service.builder()
                .name("")
                .price(service.getPrice())
                .stock(service.getStock())
                .build();

        Mockito.when(serviceMapper.fromDTO(serviceCreateDTO)).thenReturn(serv);

        Exception ex = assertThrows(ServiceBadRequestException.class, () -> {
            serviceController.newService(serviceCreateDTO);
        });

        assertTrue(ex.getMessage().contains("Nombre"));

        Mockito.verify(serviceMapper, Mockito.times(1))
                .fromDTO(serviceCreateDTO);
    }

    @Test
    @Order(6)
    void checkServiceDataPriceExceptionTest() {
        ServiceDTO serviceCreateDTO = ServiceDTO.builder()
                .name(service.getName())
                .price(service.getPrice())
                .stock(service.getStock())
                .build();

        Service serv = Service.builder()
                .name(service.getName())
                .price(-1.0)
                .stock(service.getStock())
                .build();

        Mockito.when(serviceMapper.fromDTO(serviceCreateDTO)).thenReturn(serv);

        Exception ex = assertThrows(ServiceBadRequestException.class, () -> {
            serviceController.newService(serviceCreateDTO);
        });

        assertTrue(ex.getMessage().contains("Precio"));

        Mockito.verify(serviceMapper, Mockito.times(1))
                .fromDTO(serviceCreateDTO);
    }


    @Test
    @Order(7)
    void checkServiceDataStockExceptionTest() {
        ServiceDTO serviceCreateDTO = ServiceDTO.builder()
                .name(service.getName())
                .price(service.getPrice())
                .stock(service.getStock())
                .build();

        Service serv = Service.builder()
                .name(service.getName())
                .price(service.getPrice())
                .stock(-1)
                .build();

        Mockito.when(serviceMapper.fromDTO(serviceCreateDTO)).thenReturn(serv);

        Exception ex = assertThrows(ServiceBadRequestException.class, () -> {
            serviceController.newService(serviceCreateDTO);
        });

        assertTrue(ex.getMessage().contains("Stock"));

        Mockito.verify(serviceMapper, Mockito.times(1))
                .fromDTO(serviceCreateDTO);
    }

    @Test
    @Order(8)
    void updateTestMock() {
        ServiceDTO serviceDTO = ServiceDTO.builder()
                .name(service.getName())
                .price(service.getPrice())
                .stock(service.getStock())
                .build();

        Mockito.when(serviceRepository.findById(service.getId()))
                .thenReturn(java.util.Optional.of(service));

        Mockito.when(serviceRepository.save(service))
                .thenReturn(service);

        Mockito.when(serviceMapper.toDTO(service)).thenReturn(serviceDTO);

        var response = serviceController.update(service.getId(), serviceDTO);
        var res = response.getBody();

        assertAll(
                () -> assertEquals(HttpStatus.OK.value(), response.getStatusCode().value()),
                () -> Assertions.assertEquals(res.getName(), service.getName()),
                () -> Assertions.assertEquals(res.getPrice(), service.getPrice()),
                () -> Assertions.assertEquals(res.getStock(), service.getStock())
        );

        Mockito.verify(serviceRepository, Mockito.times(1))
                .findById(service.getId());
        Mockito.verify(serviceRepository, Mockito.times(1))
                .save(service);
        Mockito.verify(serviceMapper, Mockito.times(1))
                .toDTO(service);
    }

    @Test
    @Order(5)
    void deleteTestMock() {
        ServiceDTO serviceDTO = ServiceDTO.builder()
                .name(service.getName())
                .price(service.getPrice())
                .stock(service.getStock())
                .build();

        Mockito.when(serviceRepository.findById(service.getId()))
                .thenReturn(java.util.Optional.of(service));

        Mockito.when(serviceMapper.toDTO(service)).thenReturn(serviceDTO);

        var response = serviceController.deleteService(service.getId());
        var res = response.getBody();

        assertAll(
                () -> assertEquals(HttpStatus.OK.value(), response.getStatusCode().value()),
                () -> Assertions.assertEquals(res.getName(), service.getName()),
                () -> Assertions.assertEquals(res.getPrice(), service.getPrice()),
                () -> Assertions.assertEquals(res.getStock(), service.getStock())
        );

        Mockito.verify(serviceRepository, Mockito.times(1))
                .findById(service.getId());
        Mockito.verify(serviceMapper, Mockito.times(1))
                .toDTO(service);
    }
}
