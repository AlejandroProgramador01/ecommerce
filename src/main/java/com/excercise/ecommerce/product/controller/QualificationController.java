package com.excercise.ecommerce.product.controller;

import com.excercise.ecommerce.product.dto.QualificationCreateRequestDTO;
import com.excercise.ecommerce.product.dto.QualificationResponseDTO;
import com.excercise.ecommerce.product.dto.QualificationUpdateRequestDTO;
import com.excercise.ecommerce.product.service.QualificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/qualifications")
@RequiredArgsConstructor
public class QualificationController {

    private final QualificationService qualificationService;

    @PostMapping
    public ResponseEntity<QualificationResponseDTO> create(
            @Valid @RequestBody QualificationCreateRequestDTO dto,
            Authentication authentication
    ) {
        String email = authentication.getName();
        QualificationResponseDTO response = qualificationService.createQualification(dto, email);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QualificationResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody QualificationUpdateRequestDTO dto,
            Authentication authentication
    ) {
        String email = authentication.getName();
        QualificationResponseDTO response = qualificationService.updateQualification(id, dto, email);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            Authentication authentication
    ) {
        String email = authentication.getName();
        qualificationService.deleteQualification(id, email);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<List<QualificationResponseDTO>> getByProduct(@PathVariable Long productId) {
        List<QualificationResponseDTO> qualifications = qualificationService.getProductQualifications(productId);
        return ResponseEntity.ok(qualifications);
    }
}