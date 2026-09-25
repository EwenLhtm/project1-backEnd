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
        // Verification des champs obligatoires
        Assert.notNull(etudiant, "Etudiant must not be null");
        Assert.hasText(etudiant.getFirstName(), "First name must not be null or empty");
        Assert.hasText(etudiant.getLastName(), "Last name must not be null or empty");
        Assert.hasText(etudiant.getEmail(), "Email must not be null or empty");

        // Vérification de l'unicité de l'email
        Optional<Etudiant> optionalEtudiant = etudiantRepository.findByEmail(etudiant.getEmail());
        if(optionalEtudiant.isPresent()) {
            throw new IllegalArgumentException("Etudiant with email " + etudiant.getEmail() + " already exists");
        }

        // Enregistrement de l'étudiant
        etudiantRepository.save(etudiant);
    }

    public Iterable<Etudiant> getAll(){
        // Récupération de tous les étudiants
        return etudiantRepository.findAll();
    }

    public Optional<Etudiant> getById(Long id) {
        // Vérification que l'id n'est pas null
        Assert.notNull(id, "Id must not be null");

        // Récupération de l'étudiant par id
        return etudiantRepository.findById(id);
    }

    public void update(Long id,Etudiant etudiant) {
        // Vérification des champs obligatoires et de l'id
        Assert.notNull(etudiant, "Etudiant must not be null");
        Assert.notNull(id, "Id must not be null");
        Assert.hasText(etudiant.getFirstName(), "First name must not be null or empty");
        Assert.hasText(etudiant.getLastName(), "Last name must not be null or empty");
        Assert.hasText(etudiant.getEmail(), "Email must not be null or empty");
        etudiant.setId(id);

        // Vérification de l'existence de l'étudiant à mettre à jour
        Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(etudiant.getId());
        if(optionalEtudiant.isPresent()) {
            // Si l'étudiant existe, on vérifie que l'email n'est pas déjà utilisé par un autre étudiant
            etudiantRepository.save(etudiant);
        } else {
            // Si l'étudiant n'existe pas, on lance une exception
            throw new IllegalArgumentException("Etudiant with id " + etudiant.getId() + " does not exist");
        }
    }
    
    public void delete(Long id) {
        // Vérification que l'id n'est pas null
        Assert.notNull(id, "Id must not be null");
        Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(id);
        if(optionalEtudiant.isPresent()) {
            // Si l'étudiant existe, on le supprime
            etudiantRepository.deleteById(id);
        } else {
            // Si l'étudiant n'existe pas, on lance une exception
            throw new IllegalArgumentException("Etudiant with id " + id + " does not exist");
        }
    } 
}
