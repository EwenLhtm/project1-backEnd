package com.openclassrooms.etudiant.controller;

import com.openclassrooms.etudiant.service.EtudiantService;
import com.openclassrooms.etudiant.dto.EtudiantRequestDTO;
import com.openclassrooms.etudiant.mapper.EtudiantDtoMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping 
@RequiredArgsConstructor
public class EtudiantController {
    
    private final EtudiantService etudiantService;
    private final EtudiantDtoMapper etudiantDtoMapper;

    @PostMapping("/api/etudiant")
    public ResponseEntity<?> create(@Valid @RequestBody EtudiantRequestDTO etudiantRequestDTO) {
        etudiantService.create(etudiantDtoMapper.toEntity(etudiantRequestDTO));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/api/etudiant/{id}")
    public ResponseEntity<?> getEtudiantById(@PathVariable Long id) {
        return etudiantService.getById(id)
                .map(etudiant -> new ResponseEntity<>(etudiant, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/api/etudiant")
    public ResponseEntity<?> getAllEtudiants() {
        return new ResponseEntity<>(etudiantService.getAll(), HttpStatus.OK);
    }

    @PutMapping("/api/etudiant/{id}")
    public ResponseEntity<?> updateEtudiant(@PathVariable Long id, @RequestBody EtudiantRequestDTO etudiantRequestDTO) {
        etudiantService.update(id, etudiantDtoMapper.toEntity(etudiantRequestDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/api/etudiant/{id}")
    public ResponseEntity<?> deleteEtudiant(@PathVariable Long id) {
        etudiantService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
