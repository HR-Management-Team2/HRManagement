package com.hrmanagement.mapper;

import com.hrmanagement.dto.request.AuthEmployeeUpdateRequestDto;
import com.hrmanagement.dto.request.AuthManagerUpdateRequestDto;
import com.hrmanagement.dto.request.AuthUpdateRequestDto;
import com.hrmanagement.dto.request.RegisterRequestDto;
import com.hrmanagement.dto.response.RegisterResponseDto;
import com.hrmanagement.rabbitmq.model.MailActivateModel;
import com.hrmanagement.rabbitmq.model.MailRegisterModel;
import com.hrmanagement.rabbitmq.model.UserRegisterModel;
import com.hrmanagement.repository.entity.Auth;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthMapper {
    IAuthMapper INSTANCE = Mappers.getMapper(IAuthMapper.class);

    Auth fromAuthRegisterRequestDtoToAuth(final RegisterRequestDto dto);

    RegisterResponseDto fromAuthToAuthRegisterResponseDto(final Auth auth);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Auth fromAuthUpdateDtoToAuth(AuthUpdateRequestDto dto, @MappingTarget Auth auth);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Auth fromAuthEmployeeUpdateDtoToAuth(AuthEmployeeUpdateRequestDto dto, @MappingTarget Auth auth);

    @Mapping(source = "id", target = "authId")
    UserRegisterModel fromAuthToUserRegisterModel(final Auth auth);

    MailRegisterModel fromAuthToMailRegisterModel(Auth auth);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Auth fromAuthManagerUpdateDtoToAuth(AuthManagerUpdateRequestDto dto,@MappingTarget Auth auth);

    MailActivateModel fromAuthToMailActivateModel(final Auth auth);

}
