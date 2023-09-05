package com.hrmanagement.service;

import com.hrmanagement.dto.request.CreateCompanyRequestDto;
import com.hrmanagement.mapper.ISetCompanyMapper;
import com.hrmanagement.repository.ICompanyRepository;
import com.hrmanagement.repository.entity.Company;
import com.hrmanagement.utility.ServiceManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyService extends ServiceManager<Company, String> {

    private final ICompanyRepository companyRepository;
    private final ISetCompanyMapper iSetCompanyMapper;

    public CompanyService(ICompanyRepository companyRepository, ISetCompanyMapper iSetCompanyMapper) {
        super(companyRepository);
        this.companyRepository = companyRepository;
        this.iSetCompanyMapper = iSetCompanyMapper;
    }
    @Transactional
    public Boolean createCompany(CreateCompanyRequestDto dto) {
        Company company = iSetCompanyMapper.toCompany(dto);
        save(company);
        return true;
    }
}
