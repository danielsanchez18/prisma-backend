package com.example.prisma_backend.impl;

import com.example.prisma_backend.exception.ResourceFoundException;
import com.example.prisma_backend.model.Student;
import com.example.prisma_backend.model.User;
import com.example.prisma_backend.repository.StudentRepository;
import com.example.prisma_backend.repository.UserRepository;
import com.example.prisma_backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Override
    public Student addStudent(Student student) throws ResourceFoundException {
        User user = student.getUser();
        student.setIdStudent(generateStudentId());
        user.setType("STUDENT");
        user.setActive(true);
        student.setEntryDate(LocalDate.now());
        student.setPaymentStatus(false);
        user = userServiceImpl.saveUser(user);
        student.setUser(user);
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(String id) {
        return studentRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Student> searchStudentsByFullName(String name, int page, int size) {
        return studentRepository.findStudentsByFullName(name, page, size);
    }

    @Override
    public Page<Student> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Student getStudentByDni(String dni) {
        return studentRepository.findStudentByDni(dni);
    }

    @Override
    public Student updateStudent(UUID id, Student updatedStudent) throws ResourceFoundException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            Student existingStudent = existingUser.getStudent();

            if (!existingUser.getDni().equals(updatedStudent.getUser().getDni())) {
                Optional<User> userWithSameDni = userRepository.findUserByDni(updatedStudent.getUser().getDni());
                if (userWithSameDni.isPresent()) {
                    throw new ResourceFoundException("El DNI ya está en uso por otro usuario");
                }
            }

            existingUser.setName(updatedStudent.getUser().getName());
            existingUser.setLastName(updatedStudent.getUser().getLastName());
            existingUser.setEmail(updatedStudent.getUser().getEmail());
            existingUser.setPhone(updatedStudent.getUser().getPhone());
            existingUser.setDni(updatedStudent.getUser().getDni());

            existingStudent.setGrade(updatedStudent.getGrade());
            existingStudent.setSection(updatedStudent.getSection());
            existingStudent.setStudyLevel(updatedStudent.getStudyLevel());
            existingStudent.setShift(updatedStudent.getShift());
            existingStudent.setProfile(updatedStudent.getProfile());

            userRepository.save(existingUser);
            return studentRepository.save(existingStudent);
        } else {
            throw new ResourceFoundException("Usuario no encontrado");
        }
    }


    private String generateStudentId() {
        String lastStudentId = studentRepository.findLastStudentId();
        if (lastStudentId == null) {
            return "E240000001";
        }

        String sequence = lastStudentId.substring(3);
        int sequenceNumber = Integer.parseInt(sequence);
        sequenceNumber++;

        return "E24" + String.format("%07d", sequenceNumber);
    }
}
