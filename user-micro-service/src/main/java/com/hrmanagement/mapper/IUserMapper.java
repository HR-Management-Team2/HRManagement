package com.hrmanagement.mapper;

import com.hrmanagement.dto.request.UserCreateRequestDto;
import com.hrmanagement.dto.request.UserUpdateRequestDto;
import com.hrmanagement.repository.entity.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    User fromCreateDtoToUser(UserCreateRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User fromUpdateDtoToUserProfile(UserUpdateRequestDto dto, @MappingTarget User user);
}
