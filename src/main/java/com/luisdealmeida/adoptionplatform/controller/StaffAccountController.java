package com.luisdealmeida.adoptionplatform.controller;

import com.luisdealmeida.adoptionplatform.dto.CreateStaffAccountRequest;
import com.luisdealmeida.adoptionplatform.dto.StaffAccountDto;
import com.luisdealmeida.adoptionplatform.service.StaffAccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffAccountController {

    private final StaffAccountService staffAccountService;

    public StaffAccountController(StaffAccountService staffAccountService) {
        this.staffAccountService = staffAccountService;
    }

    @PostMapping
    public ResponseEntity<StaffAccountDto> create(@Valid @RequestBody CreateStaffAccountRequest request) {
        StaffAccountDto created = staffAccountService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<StaffAccountDto>> findAll() {
        return ResponseEntity.ok(staffAccountService.findAll());
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        staffAccountService.deactivate(id);
        return ResponseEntity.noContent().build();
    }
}
