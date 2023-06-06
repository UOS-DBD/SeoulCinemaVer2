package com.dbd.seoulcinema.service;

import com.dbd.seoulcinema.dto.CreateAdminDto;
import com.dbd.seoulcinema.dto.CreateMemberDto;
import com.dbd.seoulcinema.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;


    @Transactional
    public void createAdmin(CreateAdminDto createAdminDto) {
        adminRepository.save(createAdminDto.toEntity(createAdminDto));
    }
}
