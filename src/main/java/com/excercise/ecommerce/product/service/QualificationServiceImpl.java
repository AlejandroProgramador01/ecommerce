package com.excercise.ecommerce.product.service;

import com.excercise.ecommerce.exception.DuplicateException;
import com.excercise.ecommerce.exception.NotFoundException;
import com.excercise.ecommerce.exception.UnauthorizedException;
import com.excercise.ecommerce.product.dto.QualificationCreateRequestDTO;
import com.excercise.ecommerce.product.dto.QualificationResponseDTO;
import com.excercise.ecommerce.product.dto.QualificationUpdateRequestDTO;
import com.excercise.ecommerce.product.entity.ProductEntity;
import com.excercise.ecommerce.product.entity.QualificationEntity;
import com.excercise.ecommerce.product.mapper.QualificationMapper;
import com.excercise.ecommerce.product.repository.ProductRepository;
import com.excercise.ecommerce.product.repository.QualificationRepository;
import com.excercise.ecommerce.user.entity.UserEntity;
import com.excercise.ecommerce.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QualificationServiceImpl implements QualificationService {

    private final QualificationRepository qualificationRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final QualificationMapper qualificationMapper;

    @Override
    @Transactional
    public QualificationResponseDTO createQualification(QualificationCreateRequestDTO dto, String userEmail) {
        ProductEntity product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new NotFoundException("El producto no existe"));
        UserEntity user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        qualificationRepository.findByProductAndUserId(product, user.getId())
                .ifPresent(q -> {
                    throw new DuplicateException("Ya has calificado este producto");
                });
        QualificationEntity qualification = qualificationMapper.mapToEntity(dto);
        qualification.setProduct(product);
        qualification.setUser(user);
        QualificationEntity saved = qualificationRepository.save(qualification);
        return qualificationMapper.mapToResponseDTO(saved);
    }

    @Override
    @Transactional
    public QualificationResponseDTO updateQualification(Long id, QualificationUpdateRequestDTO dto, String userEmail) {
        QualificationEntity qualification = qualificationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Calificación no encontrada"));
        if (!qualification.getUser().getEmail().equals(userEmail)) {
            throw new UnauthorizedException("No puedes modificar esta calificación");
        }
        qualification.setRating(dto.getRating());
        qualification.setComment(dto.getComment());
        QualificationEntity updated = qualificationRepository.save(qualification);
        return qualificationMapper.mapToResponseDTO(updated);
    }

    @Override
    @Transactional
    public void deleteQualification(Long id, String userEmail) {
        QualificationEntity qualification = qualificationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Calificación no encontrada"));
        if (!qualification.getUser().getEmail().equals(userEmail)) {
            throw new UnauthorizedException("No puedes eliminar esta calificación");
        }
        qualificationRepository.delete(qualification);
    }

    @Override
    public List<QualificationResponseDTO> getProductQualifications(Long productId) {
        productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("El producto no existe"));
        return qualificationRepository.findById(productId)
                .stream()
                .map(qualificationMapper::mapToResponseDTO)
                .toList();
    }
}
