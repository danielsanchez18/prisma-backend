package com.example.prisma_backend.controller;

import com.example.prisma_backend.exception.ResourceFoundException;
import com.example.prisma_backend.exception.ResourceNotFoundException;
import com.example.prisma_backend.model.Speciality;
import com.example.prisma_backend.service.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/speciality")
@CrossOrigin("*")
public class SpecialityController {

    @Autowired
    private SpecialityService specialityService;

    @PostMapping("/save")
    public Speciality saveSpeciality(@RequestBody Speciality speciality) throws ResourceFoundException {
        return specialityService.createSpeciality(speciality);
    }

    @GetMapping("/all")
    public Page<Speciality> getAllSpecialities(@PageableDefault(page = 0, size = 10) Pageable pageable){
        return specialityService.getAllSpecialities(pageable);
    }

    @GetMapping("/id/{id}")
    public Speciality getSpecialityById(@PathVariable UUID id){
        return specialityService.getSpecialityById(id);
    }

    @GetMapping("/name/{name}")
    public Speciality getSpecialityByName(@PathVariable String name){
        return specialityService.getSpecialityByName(name);
    }

    @GetMapping("/search/{name}")
    public List<Speciality> searchSpecialitiesByName(@PathVariable String name, @PageableDefault(page = 1, size = 10) Pageable pageable){
        return specialityService.searchSpecialityByName(name, pageable.getPageNumber(), pageable.getPageSize());
    }

    @PutMapping("/update/{id}")
    public Speciality updateSpeciality(@PathVariable UUID id, @RequestBody Speciality speciality) throws ResourceFoundException, ResourceNotFoundException {
        return specialityService.updateSpeciality(id, speciality);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSpeciality(@PathVariable UUID id){
        specialityService.deleteSpeciality(id);
    }

}
