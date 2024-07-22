package com.example.prisma_backend.service;

import com.example.prisma_backend.exception.ResourceFoundException;
import com.example.prisma_backend.exception.ResourceNotFoundException;
import com.example.prisma_backend.model.Speciality;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpecialityService {

    Speciality createSpeciality(Speciality speciality) throws ResourceFoundException;

    Speciality getSpecialityById(UUID id);

    List<Speciality> searchSpecialityByName(String name, int page, int size);

    Speciality getSpecialityByName(String name);

    Page<Speciality> getAllSpecialities(Pageable pageable);

    Speciality updateSpeciality(UUID id, Speciality speciality) throws ResourceFoundException, ResourceNotFoundException;

    void deleteSpeciality(UUID id);
}
