package com.madirex.hairsalonserver.mapper;

import com.madirex.hairsalonserver.dto.service.ServiceDTO;
import com.madirex.hairsalonserver.model.Service;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ServiceMapper {
    private final ModelMapper modelMapper;

    public ServiceDTO toDTO(Service service) {
        return modelMapper.map(service, ServiceDTO.class);

    }

    public Service fromDTO(ServiceDTO serviceDTO) {
        return modelMapper.map(serviceDTO, Service.class);
    }

    public List<ServiceDTO> toDTO(List<Service> services) {
        return services.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
