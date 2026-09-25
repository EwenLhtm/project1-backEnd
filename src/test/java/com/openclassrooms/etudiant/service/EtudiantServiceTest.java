package com.openclassrooms.etudiant.service;

import com.openclassrooms.etudiant.entities.Etudiant;
import com.openclassrooms.etudiant.entities.User;
import com.openclassrooms.etudiant.repository.EtudiantRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class EtudiantServiceTest {
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";
    private static final String EMAIL = "John.Doe@gmail.com";
    @Mock 
    private EtudiantRepository etudiantRepository;
    @InjectMocks
    private EtudiantService etudiantService;
    
    @Test 
    public void test_create_etudiant()  {
        // Crée un nouvel étudiant avec des informations valides
        Etudiant etudiant = new Etudiant();
        etudiant.setFirstName(FIRST_NAME);
        etudiant.setLastName(LAST_NAME);
        etudiant.setEmail(EMAIL);
        when(etudiantRepository.findByEmail(any())).thenReturn(Optional.empty());
        
        // Enregistre l'étudiant en utilisant le service
        etudiantService.create(etudiant);

        // Vérifie que l'étudiant a été enregistré correctement en utilisant un ArgumentCaptor
        ArgumentCaptor<Etudiant> etudiantCaptor = ArgumentCaptor.forClass(Etudiant.class);
        verify(etudiantRepository).save(etudiantCaptor.capture());
        assertThat(etudiantCaptor.getValue()).isEqualTo(etudiant);
    }

    @Test
    public void test_create_with_null_name_throws_IllegalArgumentException() {
        // Crée un nouvel étudiant avec un prénom null
        Etudiant etudiant = new Etudiant();
        etudiant.setFirstName(null);
        etudiant.setLastName(LAST_NAME);
        etudiant.setEmail(EMAIL);

        // Vérifie que la création de l'étudiant avec un prénom null lance une exception IllegalArgumentException
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> etudiantService.create(etudiant));
    }

    @Test 
    public void test_create_already_exist_email_throws_IllegalArgumentException() {
        // Crée un nouvel étudiant avec un email déjà existant
        Etudiant etudiant = new Etudiant();
        etudiant.setFirstName(FIRST_NAME);
        etudiant.setLastName(LAST_NAME);
        etudiant.setEmail(EMAIL);
        when(etudiantRepository.findByEmail(any())).thenReturn(Optional.of(etudiant));

        // Vérifie que la création de l'étudiant avec un email déjà existant lance une exception IllegalArgumentException
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> etudiantService.create(etudiant));
    }

    @Test
    public void test_get_all_etudiants() {
        // Crée deux étudiants avec des informations valides
        Etudiant etudiant1 = new Etudiant();
        etudiant1.setFirstName(FIRST_NAME);
        etudiant1.setLastName(LAST_NAME);
        etudiant1.setEmail(EMAIL);

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setFirstName("Jane");
        etudiant2.setLastName("Smith");
        etudiant2.setEmail("jane.smith@gmail.com");

        // Simule le comportement du repository pour retourner les deux étudiants
        when(etudiantRepository.findAll()).thenReturn(java.util.List.of(etudiant1, etudiant2));
        
        // Récupère tous les étudiants en utilisant le service
        Iterable<Etudiant> etudiants = etudiantService.getAll();

        // Vérifie que les étudiants récupérés sont bien ceux qui ont été créés
        assertThat(etudiants).containsExactly(etudiant1, etudiant2);
    }

    @Test 
    public void test_get_etudiant() {
        // Crée un étudiant avec des informations valides
        Long id = 1L;
        Etudiant etudiant = new Etudiant();
        etudiant.setId(id);
        etudiant.setFirstName(FIRST_NAME);
        etudiant.setLastName(LAST_NAME);
        etudiant.setEmail(EMAIL);
        when(etudiantRepository.findById(id)).thenReturn(Optional.of(etudiant));

        // Récupère l'étudiant en utilisant le service
        Optional<Etudiant> result = etudiantService.getById(id);

        // Vérifie que l'étudiant récupéré est bien celui qui a été créé
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(etudiant);
    }

    @Test 
    public void test_get_etudiant_with_non_existing_id_returns_empty() {
        // Crée un étudiant avec un ID non existant
        Long id = 1L;
        when(etudiantRepository.findById(id)).thenReturn(Optional.empty());

        // Récupère l'étudiant en utilisant le service
        Optional<Etudiant> result = etudiantService.getById(id);

        // Vérifie que le résultat est vide car l'étudiant avec l'ID spécifié n'existe pas
        assertThat(result).isNotPresent();
    }

    @Test 
    public void test_update_etudiant() {
        // Crée un étudiant avec des informations valides
        Long id = 1L;
        Etudiant etudiant = new Etudiant();
        etudiant.setId(id);
        etudiant.setFirstName(FIRST_NAME);
        etudiant.setLastName(LAST_NAME);
        etudiant.setEmail(EMAIL);
        when(etudiantRepository.findById(id)).thenReturn(Optional.of(etudiant));

        // Met à jour l'étudiant en utilisant le service
        etudiantService.update(id, etudiant);

        // Vérifie que l'étudiant a été mis à jour dans le repository
        ArgumentCaptor<Etudiant> etudiantCaptor = ArgumentCaptor.forClass(Etudiant.class);
        verify(etudiantRepository).save(etudiantCaptor.capture());
        assertThat(etudiantCaptor.getValue()).isEqualTo(etudiant);
    }

    @Test 
    public void test_update_non_existing_etudiant_throws_IllegalArgumentException() {
        // Crée un étudiant avec un ID non existant
        Long id = 1L;
        Etudiant etudiant = new Etudiant();
        etudiant.setId(id);
        etudiant.setFirstName(FIRST_NAME);
        etudiant.setLastName(LAST_NAME);
        etudiant.setEmail(EMAIL);
        when(etudiantRepository.findById(id)).thenReturn(Optional.empty());

        // Vérifie que la mise à jour d'un étudiant avec un ID non existant lance une exception IllegalArgumentException
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> etudiantService.update(id, etudiant));
    }

    @Test
    public void test_update_with_null_name_throws_IllegalArgumentException() {
        // Crée un étudiant avec un prénom null
        Long id = 1L;
        Etudiant etudiant = new Etudiant();
        etudiant.setId(id);
        etudiant.setFirstName(null);
        etudiant.setLastName(LAST_NAME);
        etudiant.setEmail(EMAIL);

        // Vérifie que la mise à jour d'un étudiant avec un prénom null lance une exception IllegalArgumentException
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> etudiantService.update(id, etudiant));
    }

    @Test 
    public void test_delete_etudiant() {
        // Crée un étudiant avec des informations valides
        Long id = 1L;
        Etudiant etudiant = new Etudiant();
        etudiant.setId(id);
        etudiant.setFirstName(FIRST_NAME);
        etudiant.setLastName(LAST_NAME);
        etudiant.setEmail(EMAIL);
        when(etudiantRepository.findById(id)).thenReturn(Optional.of(etudiant));

        // Supprime l'étudiant en utilisant le service
        etudiantService.delete(id);

        // Vérifie que l'étudiant a été supprimé du repository
        verify(etudiantRepository).deleteById(id);
    }

    @Test 
    public void test_delete_non_existing_etudiant_throws_IllegalArgumentException() {
        // Crée un étudiant avec un ID non existant
        Long id = 1L;
        when(etudiantRepository.findById(id)).thenReturn(Optional.empty());

        // Vérifie que la suppression d'un étudiant avec un ID non existant lance une exception IllegalArgumentException
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> etudiantService.delete(id));
    }
}