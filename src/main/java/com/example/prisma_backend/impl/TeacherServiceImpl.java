package com.example.prisma_backend.impl;

import com.example.prisma_backend.exception.ResourceFoundException;
import com.example.prisma_backend.model.Speciality;
import com.example.prisma_backend.model.Teacher;
import com.example.prisma_backend.model.User;
import com.example.prisma_backend.repository.SpecialityRepository;
import com.example.prisma_backend.repository.TeacherRepository;
import com.example.prisma_backend.repository.UserRepository;
import com.example.prisma_backend.service.TeacherService;
import com.example.prisma_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SpecialityRepository specialityRepository;

    @Override
    public Teacher addTeacher(Teacher teacher) throws ResourceFoundException {
        User user = teacher.getUser();
        teacher.setIdTeacher(generateTeacherId());
        user.setType("TEACHER");
        user.setActive(true);
        teacher.setEntryDate(LocalDate.now());

        if (teacher.getSpeciality() != null) {
            Speciality speciality = specialityRepository.findById(teacher.getSpeciality().getIdSpeciality()).
                    orElseThrow(() -> new ResourceFoundException("Especialidad no encontrada"));
            teacher.setSpeciality(speciality);
        }

        user = userServiceImpl.saveUser(user);
        teacher.setUser(user);

        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher getTeacherById(String id) {
        return teacherRepository.findById(id).orElseThrow();
    }

    @Override
    public Page<Teacher> getAllTeachers(Pageable pageable) {
        return teacherRepository.findAll(pageable);
    }

    @Override
    public List<Teacher> searchTeacherByFullName(String name, int page, int size) {
        return teacherRepository.findTeachersByFullName(name, page, size);
    }

    @Override
    public Teacher getTeacherByDni(String dni) {
        return teacherRepository.findTeacherByDni(dni);
    }

    @Override
    public Teacher updateTeacher(UUID id, Teacher updatedTeacher) throws ResourceFoundException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            Teacher existingTeacher = existingUser.getTeacher();

            if (!existingUser.getDni().equals(updatedTeacher.getUser().getDni())) {
                Optional<User> userWithSameDni = userRepository.findUserByDni(updatedTeacher.getUser().getDni());
                if (userWithSameDni.isPresent()) {
                    throw new ResourceFoundException("El DNI ya está en uso por otro usuario");
                }
            }

            existingUser.setName(updatedTeacher.getUser().getName());
            existingUser.setLastName(updatedTeacher.getUser().getLastName());
            existingUser.setEmail(updatedTeacher.getUser().getEmail());
            existingUser.setPhone(updatedTeacher.getUser().getPhone());
            existingUser.setDni(updatedTeacher.getUser().getDni());

            existingTeacher.setProfile(updatedTeacher.getProfile());
            existingTeacher.setSpeciality(updatedTeacher.getSpeciality());

            userRepository.save(existingUser);
            return teacherRepository.save(existingTeacher);
        } else {
            throw new ResourceFoundException("Usuario no encontrado");
        }
    }

    private String generateTeacherId() {
        String lastTeacherId = teacherRepository.findLastTeacherId();
        if (lastTeacherId == null) {
            return "D240000001";
        }

        String sequence = lastTeacherId.substring(3);
        int sequenceNumber = Integer.parseInt(sequence);
        sequenceNumber++;

        return "D24" + String.format("%07d", sequenceNumber);
    }
}
