package com.hrmanagement.mapper;

import com.hrmanagement.dto.request.AuthEmployeeUpdateRequestDto;
import com.hrmanagement.dto.request.AuthUpdateRequestDto;
import com.hrmanagement.dto.request.UserCreateRequestDto;
import com.hrmanagement.dto.request.UserUpdateRequestDto;
import com.hrmanagement.dto.response.AdminProfileResponseDto;
import com.hrmanagement.dto.response.UserResponseDto;
import com.hrmanagement.dto.response.EmployeeListResponseDto;
import com.hrmanagement.rabbitmq.model.UserRegisterModel;
import com.hrmanagement.repository.entity.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    User fromCreateDtoToUser(UserCreateRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User fromUpdateDtoToUserProfile(UserUpdateRequestDto dto, @MappingTarget User user);

    User fromRegisterModelToUser(UserRegisterModel model);

    AuthUpdateRequestDto fromUserToAuthUpdateDto(User user);

    UserResponseDto fromUserToResponseDto(User user);

    AdminProfileResponseDto fromUserToAdminResponseDto(User user);

    AuthEmployeeUpdateRequestDto fromUserToAuthEmployeeUpdateDto(User user);
    EmployeeListResponseDto toEmployeeListDto(User user);
}
