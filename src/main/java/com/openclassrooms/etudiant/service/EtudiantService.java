package com.openclassrooms.etudiant.service;

import org.springframework.stereotype.Service;

import com.openclassrooms.etudiant.entities.Etudiant;
import com.openclassrooms.etudiant.repository.EtudiantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.Optional;

@Slf4j 
@Service 
@Transactional 
@RequiredArgsConstructor 
public class EtudiantService {
    private final EtudiantRepository etudiantRepository;

    public void create(Etudiant etudiant) {
        Assert.notNull(etudiant, "Etudiant must not be null");

        Optional<Etudiant> optionalEtudiant = etudiantRepository.findByEmail(etudiant.getEmail());
        if(optionalEtudiant.isPresent()) {
            throw new IllegalArgumentException("Etudiant with email " + etudiant.getEmail() + " already exists");
        }
        etudiantRepository.save(etudiant);
    }

    public Iterable<Etudiant> getAll(){
        return etudiantRepository.findAll();
    }

    public Optional<Etudiant> getById(Long id) {
        Assert.notNull(id, "Id must not be null");
        return etudiantRepository.findById(id);
    }

    public void update(Long id,Etudiant etudiant) {
        Assert.notNull(etudiant, "Etudiant must not be null");
        etudiant.setId(id);
        Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(etudiant.getId());
        if(optionalEtudiant.isPresent()) {
            etudiantRepository.save(etudiant);
        } else {
            throw new IllegalArgumentException("Etudiant with id " + etudiant.getId() + " does not exist");
        }
    }
    
    public void delete(Long id) {
        Assert.notNull(id, "Id must not be null");
        Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(id);
        if(optionalEtudiant.isPresent()) {
            etudiantRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Etudiant with id " + id + " does not exist");
        }
    } 
}
