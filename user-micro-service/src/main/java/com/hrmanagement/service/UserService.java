package com.hrmanagement.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hrmanagement.config.CloudinaryConfig;
import com.hrmanagement.dto.request.AuthUpdateRequestDto;
import com.hrmanagement.dto.request.EmployeeCreateRequestDto;
import com.hrmanagement.dto.request.UserCreateRequestDto;
import com.hrmanagement.dto.request.UserUpdateRequestDto;
import com.hrmanagement.dto.response.*;
import com.hrmanagement.dto.request.*;
import com.hrmanagement.exceptions.ErrorType;
import com.hrmanagement.exceptions.UserManagerException;
import com.hrmanagement.manager.IAuthManager;
import com.hrmanagement.mapper.IPermissionMapper;
import com.hrmanagement.mapper.IUserMapper;
import com.hrmanagement.rabbitmq.model.CreateEmployee;
import com.hrmanagement.rabbitmq.model.UserRegisterModel;
import com.hrmanagement.rabbitmq.producer.EmployeeProducer;
import com.hrmanagement.repository.IAdvanceRepository;
import com.hrmanagement.repository.IPermissionRepository;
import com.hrmanagement.repository.IUserRepository;
import com.hrmanagement.repository.entity.Advance;
import com.hrmanagement.repository.entity.Permission;
import com.hrmanagement.repository.entity.User;
import com.hrmanagement.repository.enums.ERole;
import com.hrmanagement.repository.enums.EStatus;
import com.hrmanagement.utility.JwtTokenManager;
import com.hrmanagement.utility.ServiceManager;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import static com.hrmanagement.repository.enums.EApprovalStatus.*;

@Service
public class UserService extends ServiceManager<User,String> {
    private final IUserRepository repository;
    private final IAdvanceRepository advanceRepository;
    private final IPermissionRepository permissionRepository;
    private final JwtTokenManager jwtTokenManager;
    private final IAuthManager authManager;
    private final EmployeeProducer employeeProducer;
    private final CloudinaryConfig cloudinaryConfig;

    public UserService(IUserRepository repository, IAdvanceRepository advanceRepository, IPermissionRepository permissionRepository, JwtTokenManager jwtTokenManager,
                       IAuthManager authManager, EmployeeProducer employeeProducer, CloudinaryConfig cloudinaryConfig) {
        super(repository);
        this.repository = repository;
        this.advanceRepository = advanceRepository;
        this.permissionRepository = permissionRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.authManager = authManager;
        this.employeeProducer = employeeProducer;
        this.cloudinaryConfig = cloudinaryConfig;
    }

    public Boolean createUser(UserRegisterModel model) {
        repository.save(IUserMapper.INSTANCE.fromRegisterModelToUser(model));
        return true;
    }

    public Boolean createUserBasic(UserCreateRequestDto dto) {
        repository.save(IUserMapper.INSTANCE.fromCreateDtoToUser(dto));
        return true;
    }

    @Transactional
    public Boolean updateUser(String token, UserUpdateRequestDto dto) {

        Optional<Long> authId = jwtTokenManager.getIdFromToken(token);
        if (authId.isEmpty()) {
            throw new UserManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<User> user = repository.findOptionalByAuthId(authId.get());
        if (user.isPresent()) {
            repository.save(IUserMapper.INSTANCE.fromUpdateDtoToUserProfile(dto, user.get()));
            AuthUpdateRequestDto authUpdateRequestDto = IUserMapper.INSTANCE.fromUserToAuthUpdateDto(user.get());
            authManager.updateAuth(authUpdateRequestDto);
            return true;
        }
        throw new RuntimeException("Hata");
    }

    public Boolean deleteById(Long authId) {
        System.out.println(authId);
        Optional<User> user = repository.findOptionalByAuthId(authId);

        user.orElseThrow(() -> {
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        });

        user.get().setStatus(EStatus.DELETED);
        update(user.get());
        return true;
    }


    /*public Boolean activateStatus(Long authId) {
        Optional<User> userProfile = repository.findOptionalByAuthId(authId);
        userProfile.orElseThrow(() -> {
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        });

        userProfile.get().setStatus(EStatus.ACTIVE);
        update(userProfile.get());
        return true;

    }*/

    public Boolean activateStatus(String token) {
        System.out.println("Service' e gelen token --> " + token);
        Optional<Long> authId = jwtTokenManager.getIdFromToken(token.substring(7));
        System.out.println("Service' de substring edilmiş token --> " + token.substring(7));
        if (authId.isEmpty()) {
            throw new UserManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<User> user = repository.findOptionalByAuthId(authId.get());
        if (user.isEmpty()) {
            throw new RuntimeException("Auth id bulunamadı");
        }
        user.get().setStatus(EStatus.ACTIVE);
        update(user.get());
        return true;
    }

    @Transactional
    public Boolean activateStatusManager(Long authId) {
        Optional<User> optionalUser = repository.findOptionalByAuthId(authId);
        if (optionalUser.isEmpty()){
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        }
        if(optionalUser.get().getStatus().equals(EStatus.ACTIVE)){
            throw new UserManagerException(ErrorType.ALREADY_ACTIVE);
        }
        if (!optionalUser.get().getStatus().equals(EStatus.PENDING)){
            throw new UserManagerException(ErrorType.USER_ACCESS_ERROR);
        }else {
            optionalUser.get().setStatus(EStatus.ACTIVE);
            update(optionalUser.get());
            //userprofilemanager yazılıp daha sonra user'a gönderilecek bu kısımda
            authManager.activateStatusManager(optionalUser.get().getAuthId());
            return true;
        }
    }

//    public Boolean createEmployee(EmployeeCreateRequestDto dto){
//        User user = User.builder()
//                .name(dto.getName())
//                .surname(dto.getSurname())
//                .idNumber(dto.getIdNumber())
//                .email(dto.getEmail())
//                .password(dto.getPassword())
//                .address(dto.getAddress())
//                .phone(dto.getPhone())
//                .birthday(dto.getBirthday())
//                .birthdayPlace(dto.getBirthdayPlace())
//                .company_name(dto.getCompany_name())
//                .occupation(dto.getOccupation())
//                .salary(dto.getSalary())
//                .role(ERole.EMPLOYEE)
//                .build();
//        if (repository.findOptionalByEmail(dto.getEmail()).isPresent()){
//            throw new UserManagerException(ErrorType.EMAIL_DUPLICATE);
//        }
//        repository.save(user);
//        return true;
//    }


    public Boolean addEmployee(EmployeeCreateRequestDto dto) {

        Optional<Long> authId = jwtTokenManager.getIdFromToken(dto.getToken());
        if (authId.isEmpty()) {
            throw new UserManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<User> user = repository.findOptionalByAuthId(authId.get());
        if (user.isEmpty()) {
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        }
        Long idEmployee = employeeProducer.sendEmployeeAuth(CreateEmployee.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .companyName(user.get().getCompanyName())
                .taxNo(user.get().getTaxNo()).build());
        System.out.println(idEmployee);
        if(idEmployee!=0L) {
            Optional<User> employeeOptional=repository.findOptionalByIdNumber(dto.getIdNumber());
            if (employeeOptional.isPresent()) throw new UserManagerException(ErrorType.EMPLOYEE_HAS_BEEN);
                User employee = User.builder()
                        .name(dto.getName())
                        .surname(dto.getSurname())
                        .idNumber(dto.getIdNumber())
                        .email(dto.getEmail())
                        .address(dto.getAddress())
                        .phone(dto.getPhone())
                        .birthday(dto.getBirthday())
                        .birthdayPlace(dto.getBirthdayPlace())
                        .companyName(user.get().getCompanyName())
                        .taxNo(user.get().getTaxNo())
                        .occupation(dto.getOccupation())
                        .salary(dto.getSalary())
                        .authId(idEmployee)
                        .role(ERole.EMPLOYEE)
                        .status(EStatus.ACTIVE)
                        .build();
                repository.save(employee);
        }
        return true;
        }

        public AdminProfileResponseDto findUser(Long authId){
        Optional<User> user = repository.findOptionalByAuthId(authId);
        if (user.isEmpty()){
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        }
        AdminProfileResponseDto adminProfileResponseDto = IUserMapper.INSTANCE.fromUserToAdminResponseDto(user.get());
        return adminProfileResponseDto;
        }

   /* public AdminProfileResponseDto findUser(String token){
        Optional<Long> userId = jwtTokenManager.getIdFromToken(token);
        if (userId.isEmpty())
            throw new UserManagerException(ErrorType.INVALID_TOKEN);
        Optional<User> userOptional = repository.findById(userId.get());
        if (userOptional.isEmpty())
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        return AdminProfileResponseDto.builder()
                .data(userOptional.get())
                .message("Kullanıcı bilgileri getirildi")
                .statusCode(200)
                .build();
    }*/






    public List<EmployeeListResponseDto> findAllEmployee(String token) {

        Optional<Long> authId = jwtTokenManager.getIdFromToken(token);
        if (authId.isEmpty()) {
            throw new UserManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<User> user = repository.findOptionalByAuthId(authId.get());
        if (user.isEmpty()) {
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        }
        return repository.findAllByTaxNo(user.get().getTaxNo()).stream().map(x->{
            return EmployeeListResponseDto.builder().name(x.getName()).surname(x.getSurname()).email(x.getEmail()).address(x.getAddress()).phone(x.getPhone()).occupation(x.getOccupation()).build();
        }).collect(Collectors.toList());
    }

    public Boolean updateEmployee(UpdateEmployeeRequestDto dto) {
        User userEmp= repository.findOptionalByEmail(dto.getEmail())
                .orElseThrow(()->new UserManagerException(ErrorType.USER_NOT_FOUND));
        userEmp.setName(dto.getName());
        userEmp.setSurname(dto.getSurname());
        userEmp.setIdNumber(dto.getIdNumber());
        userEmp.setEmail(dto.getEmail());
        userEmp.setAddress(dto.getAddress());
        userEmp.setPhone(dto.getPhone());
        userEmp.setBirthday(dto.getBirthday());
        userEmp.setBirthdayPlace(dto.getBirthdayPlace());
        userEmp.setOccupation(dto.getOccupation());
        userEmp.setSalary(dto.getSalary());
        repository.save(userEmp);
        AuthEmployeeUpdateRequestDto authEmployeeUpdateRequestDto=IUserMapper.INSTANCE.fromUserToAuthEmployeeUpdateDto(userEmp);
        authManager.updateAuthEmployee(authEmployeeUpdateRequestDto);
        return true;
    }

    public String imageUpload(MultipartFile file){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", cloudinaryConfig.getCloud_name());
        config.put("api_key", cloudinaryConfig.getApi_key());
        config.put("api_secret", cloudinaryConfig.getApi_secret());

        Cloudinary cloudinary = new Cloudinary(config);

        try {
            Map<?,?> result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String url = (String) result.get("url");
            return url;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String updateImage(MultipartFile file, String token){
        Long id = jwtTokenManager.getIdFromToken(token).get();
        Optional<User> user = repository.findById(String.valueOf(id));
        if (user.isEmpty()){
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        }
        String url = imageUpload(file);
        return url;
    }

    public Boolean updateUserInfo(UpdateUserInfoRequestDto dto){
        Optional<Long> authId = jwtTokenManager.getIdFromToken(dto.getToken());
        if (authId.isEmpty()) throw new UserManagerException(ErrorType.INVALID_TOKEN);
        Optional<User> user = repository.findOptionalByAuthId(authId.get());
        if (user.isPresent()){
            user.get().setName(dto.getName());
            user.get().setSurname(dto.getSurname());
            user.get().setEmail(dto.getEmail());
            user.get().setImage(imageUpload(dto.getImage()));
            update(user.get());
            AuthEmployeeUpdateRequestDto authEmployeeUpdateRequestDto = IUserMapper.INSTANCE.fromUserToAuthEmployeeUpdateDto(user.get());
            authManager.updateAuthEmployee(authEmployeeUpdateRequestDto);
        }
        return true;
    }


    public List<ManagerListResponseDto> findAllManager() {
        EStatus statusToDelete= EStatus.DELETED;
        return repository.findAllByStatusNot(statusToDelete).stream().map(x->{
            return ManagerListResponseDto.builder().authId(x.getAuthId()).name(x.getName()).surname(x.getSurname()).email(x.getEmail()).companyName(x.getCompanyName()).taxNo(x.getTaxNo()).status(x.getStatus())
                    .build();
        }).collect(Collectors.toList());
    }
    public Boolean updateManager(ManagerUpdateRequestDto dto)  {
        User userManager=repository.findOptionalByAuthId(dto.getAuthId())
                .orElseThrow(()->new UserManagerException(ErrorType.USER_NOT_FOUND));
        userManager.setName(dto.getName());
        userManager.setSurname(dto.getSurname());
        userManager.setEmail(dto.getEmail());
        userManager.setCompanyName(dto.getCompanyName());
        userManager.setTaxNo(dto.getTaxNo());
        userManager.setStatus(dto.getStatus());
        repository.save(userManager);
        AuthManagerUpdateRequestDto authManagerUpdateRequestDto=IUserMapper.INSTANCE.fromUserToAuthManagerUpdateRequestDto(userManager);
        authManager.updateAuthManager(authManagerUpdateRequestDto);
        return true;

    }
    @Transactional
    public Boolean deleteManager(Long authId){
        Optional<User> userManager=repository.findOptionalByAuthId(authId);
        if(userManager.isEmpty()) {
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        }
        userManager.get().setStatus(EStatus.DELETED);
        repository.save(userManager.get());
        authManager.deleteAuthManager(userManager.get().getAuthId());
        return true;
    }

    public Boolean createAdvance(CreateAdvanceRequestDto createAdvanceRequestDto) {

        Optional<Long> authId = jwtTokenManager.getIdFromToken(createAdvanceRequestDto.getToken());
        if (authId.isEmpty()) throw new UserManagerException(ErrorType.INVALID_TOKEN);
        Optional<User> user = repository.findOptionalByAuthId(authId.get());
        if (user.isEmpty()){
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        }
        Advance advance = Advance.builder()
                .advanceAmount(createAdvanceRequestDto.getAdvanceAmount())
                .advanceRequestType(createAdvanceRequestDto.getAdvanceRequestType())
                .dateOfRequest(LocalDate.now())
                .currency(createAdvanceRequestDto.getCurrency())
                .nameOfTheRequester(user.get().getName())
                .surnameOfTheRequester(user.get().getSurname())
                .authId(user.get().getAuthId())
                .description(createAdvanceRequestDto.getDescription())
                .approvalStatus(PENDING_APPROVAL)
                .companyName(user.get().getCompanyName())
                .taxNo(user.get().getTaxNo())
                .build();
        advanceRepository.save(advance);
        return true;

    }
    public List<AdvanceListResponseDto> findAllAdvancesForEmployee (String token) {
        Optional<Long> authId = jwtTokenManager.getIdFromToken(token);
        if (authId.isEmpty()) {
            throw new UserManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<User> user = repository.findOptionalByAuthId(authId.get());
        if (user.isEmpty()) {
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        }
        return advanceRepository.findAllByAuthId(authId.get()).stream().map(a->{
            return AdvanceListResponseDto.builder()
                    .advanceAmount(a.getAdvanceAmount())
                    .advanceRequestType(a.getAdvanceRequestType())
                    .replyDate(a.getReplyDate())
                    .approvalStatus(a.getApprovalStatus())
                    .description(a.getDescription())
                    .currency(a.getCurrency()).build();
        }).collect(Collectors.toList());


    }

    public List<AdvanceListManagerResponseDto> findAllAdvancesForManager (String token) {
        Optional<Long> authId = jwtTokenManager.getIdFromToken(token);
        if (authId.isEmpty()) {
            throw new UserManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<User> user = repository.findOptionalByAuthId(authId.get());
        if (user.isEmpty()) {
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        }
        return advanceRepository.findAllByTaxNoAndCompanyName(user.get().getTaxNo(),user.get().getCompanyName()).stream().map(a->{
            return AdvanceListManagerResponseDto.builder()
                    .id(a.getId())
                    .nameOfTheRequester(a.getNameOfTheRequester())
                    .surnameOfTheRequester(a.getSurnameOfTheRequester())
                    .advanceAmount(a.getAdvanceAmount())
                    .advanceRequestType(a.getAdvanceRequestType())
                    .dateOfRequest(a.getDateOfRequest())
                    .approvalStatus(a.getApprovalStatus())
                    .description(a.getDescription())
                    .currency(a.getCurrency()).build();
        }).collect(Collectors.toList());
    }

    public Boolean updateStatusAdvance(UpdateStatusRequestDto dto){
        Optional<Advance> advance = advanceRepository.findById(dto.getId());
        if (advance.isEmpty()){
            throw new UserManagerException(ErrorType.REQUEST_NOT_FOUND);
        }
        advance.get().setApprovalStatus(dto.getApprovalStatus());
        advance.get().setReplyDate(LocalDate.now());
        advanceRepository.save(advance.get());
        return true;
    }



    public Boolean createPermission(CreatePermissionRequestDto dto){
        Optional<Long> authId = jwtTokenManager.getIdFromToken(dto.getToken());
        if (authId.isEmpty()) throw new UserManagerException(ErrorType.INVALID_TOKEN);
        Optional<User> user = repository.findOptionalByAuthId(authId.get());
        if (user.isEmpty()){
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        }
        Permission permission = Permission.builder()
                .epermissionType(dto.getEpermissionType())
                .dateOfRequest(LocalDate.now())
                .nameEmployee(user.get().getName())
                .surnameEmployee(user.get().getSurname())
                .authId(user.get().getAuthId())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .approvalStatus(PENDING_APPROVAL)
                .build();
        permissionRepository.save(permission);
        return true;
    }

    public Boolean updateStatusPermission(UpdateStatusRequestDto dto){
        Optional<Permission> permission = permissionRepository.findById(dto.getId());
        if (permission.isEmpty()){
            throw new UserManagerException(ErrorType.REQUEST_NOT_FOUND);
        }
        permission.get().setApprovalStatus(dto.getApprovalStatus());
        permission.get().setReplyDate(LocalDate.now());
        permissionRepository.save(permission.get());
        return true;
    }

    public List<PermissionResponseDto> findAllPermissionForEmployee(String token){
        Optional<Long> authId = jwtTokenManager.getIdFromToken(token);
        if (authId.isEmpty()) {
            throw new UserManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<User> user = repository.findOptionalByAuthId(authId.get());
        if (user.isEmpty()) {
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        }
        return permissionRepository.findOptionalByAuthId(user.get().getAuthId()).stream().map(a->{
            return PermissionResponseDto.builder()
                    .id(a.getId())
                    .nameEmployee(a.getNameEmployee())
                    .surnameEmployee(a.getSurnameEmployee())
                    .epermissionType(a.getEpermissionType())
                    .startDate(a.getStartDate())
                    .endDate(a.getEndDate())
                    .dateOfRequest(a.getDateOfRequest())
                    .approvalStatus(a.getApprovalStatus())
                    .replyDate(a.getReplyDate())
                    .days(calculatePermissionDays(a.getStartDate(),a.getEndDate()))
                    .build();
        }).collect(Collectors.toList());
        /*Optional<Long> authId = jwtTokenManager.getIdFromToken(token);
        List<Permission> permissions = permissionRepository.findOptionalByAuthId(authId.get());
        List<PermissionResponseDto> responseDtos = new ArrayList<>();
        for (Permission permission: permissions){
            responseDtos.add(IPermissionMapper.INSTANCE.fromPermissionToResponseDto(permission));
        }
        return responseDtos;*/
    }

    private static int calculatePermissionDays(LocalDate startDate, LocalDate endDate) {
        return (int) ChronoUnit.DAYS.between(startDate, endDate) + 1;
    }


    public List<PermissionListManagerResponseDto> findAllPermissionsForManager (String token) {
        Optional<Long> authId = jwtTokenManager.getIdFromToken(token);
        if (authId.isEmpty()) {
            throw new UserManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<User> user = repository.findOptionalByAuthId(authId.get());
        if (user.isEmpty()) {
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        }
        return permissionRepository.findAllByTaxNoAndCompanyName(user.get().getTaxNo(),user.get().getCompanyName()).stream().map(a->{
            return PermissionListManagerResponseDto.builder()
                    .id(a.getId())
                    .nameEmployee(a.getNameEmployee())
                    .surnameEmployee(a.getSurnameEmployee())
                    .epermissionType(a.getEpermissionType())
                    .dateOfRequest(a.getDateOfRequest())
                    .approvalStatus(a.getApprovalStatus())
                    .startDate(a.getStartDate())
                    .endDate(a.getEndDate())
                    .days(calculatePermissionDays(a.getStartDate(),a.getEndDate()))
                    .build();
        }).collect(Collectors.toList());
    }



}
