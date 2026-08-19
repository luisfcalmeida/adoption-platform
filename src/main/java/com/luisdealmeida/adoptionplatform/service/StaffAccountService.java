package com.luisdealmeida.adoptionplatform.service;

import com.luisdealmeida.adoptionplatform.dto.CreateStaffAccountRequest;
import com.luisdealmeida.adoptionplatform.dto.StaffAccountDto;
import com.luisdealmeida.adoptionplatform.entity.StaffAccount;
import com.luisdealmeida.adoptionplatform.repository.StaffAccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffAccountService {

    private final StaffAccountRepository staffAccountRepository;
    private final PasswordEncoder passwordEncoder;

    public StaffAccountService(StaffAccountRepository staffAccountRepository, PasswordEncoder passwordEncoder) {
        this.staffAccountRepository = staffAccountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public StaffAccountDto create(CreateStaffAccountRequest request) {
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        StaffAccount staffAccount = new StaffAccount(request.getEmail(), hashedPassword);
        StaffAccount saved = staffAccountRepository.save(staffAccount);
        return toDto(saved);
    }

    public List<StaffAccountDto> findAll() {
        return staffAccountRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public void deactivate(Long id) {
        StaffAccount staffAccount = staffAccountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Staff account not found: " + id));
        staffAccount.setActive(false);
        staffAccountRepository.save(staffAccount);
    }

    private StaffAccountDto toDto(StaffAccount staffAccount) {
        return new StaffAccountDto(
                staffAccount.getId(),
                staffAccount.getEmail(),
                staffAccount.isActive(),
                staffAccount.getCreatedAt()
        );
    }
}
