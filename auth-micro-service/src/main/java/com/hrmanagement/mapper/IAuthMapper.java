package com.hrmanagement.mapper;

import com.hrmanagement.dto.request.AuthUpdateRequestDto;
import com.hrmanagement.dto.request.RegisterRequestDto;
import com.hrmanagement.dto.response.RegisterResponseDto;
import com.hrmanagement.rabbitmq.model.UserRegisterModel;
import com.hrmanagement.repository.entity.Auth;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthMapper {
    IAuthMapper INSTANCE = Mappers.getMapper(IAuthMapper.class);

    Auth fromAuthRegisterRequestDtoToAuth(final RegisterRequestDto dto);

    RegisterResponseDto fromAuthToAuthRegisterResponseDto(final Auth auth);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Auth fromAuthUpdateDtoToAuth(AuthUpdateRequestDto dto, @MappingTarget Auth auth);

    @Mapping(source = "id", target = "authId")
    UserRegisterModel fromAuthToUserRegisterModel(final Auth auth);

}
