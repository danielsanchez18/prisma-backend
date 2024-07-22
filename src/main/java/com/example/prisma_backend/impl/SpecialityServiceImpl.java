package com.example.prisma_backend.impl;

import com.example.prisma_backend.exception.ResourceFoundException;
import com.example.prisma_backend.exception.ResourceNotFoundException;
import com.example.prisma_backend.model.Speciality;
import com.example.prisma_backend.repository.SpecialityRepository;
import com.example.prisma_backend.service.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SpecialityServiceImpl implements SpecialityService {

    @Autowired
    private SpecialityRepository specialityRepository;

    @Override
    public Speciality createSpeciality(Speciality speciality) throws ResourceFoundException {
        Speciality specialityWithSameNameOptional = specialityRepository.findSpecialityByName(speciality.getNameSpeciality());
        if (specialityWithSameNameOptional != null) {
            throw new ResourceFoundException("Ya existe una especialidad con el mismo nombre");
        }
        return specialityRepository.save(speciality);
    }

    @Override
    public Speciality getSpecialityById(UUID id) {
        return specialityRepository.findById(id).orElse(null);
    }

    @Override
    public List<Speciality> searchSpecialityByName(String name, int page, int size) {
        return specialityRepository.searchSpecialityByName(name, page, size);
    }

    @Override
    public Speciality getSpecialityByName(String name) {
        return specialityRepository.findSpecialityByName(name);
    }

    @Override
    public Page<Speciality> getAllSpecialities(Pageable pageable) {
        return specialityRepository.findAll(pageable);
    }

    @Override
    public Speciality updateSpeciality(UUID id, Speciality updatedSpeciality) throws ResourceFoundException, ResourceNotFoundException {
        Optional<Speciality> existingSpecialityOptional = specialityRepository.findById(id);
        if (existingSpecialityOptional.isPresent()) {
            Speciality existingSpeciality = existingSpecialityOptional.get();

            if (!existingSpeciality.getNameSpeciality().equals(updatedSpeciality.getNameSpeciality())) {
                Speciality specialityWithSameNameOptional = specialityRepository.findSpecialityByName(updatedSpeciality.getNameSpeciality());
                if (specialityWithSameNameOptional != null) {
                    throw new ResourceFoundException("Speciality with the same name already exists");
                }
            }

            existingSpeciality.setNameSpeciality(updatedSpeciality.getNameSpeciality());
            return specialityRepository.save(existingSpeciality);

        } else {
            throw new ResourceNotFoundException("Speciality not found");
        }
    }

    @Override
    public void deleteSpeciality(UUID id) {
        specialityRepository.deleteById(id);
    }
}
