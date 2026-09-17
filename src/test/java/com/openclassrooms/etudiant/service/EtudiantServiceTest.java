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
        // GIVEN
        Etudiant etudiant = new Etudiant();
        etudiant.setFirstName(FIRST_NAME);
        etudiant.setLastName(LAST_NAME);
        etudiant.setEmail(EMAIL);
        when(etudiantRepository.findByEmail(any())).thenReturn(Optional.empty());
        
        // WHEN
        etudiantService.create(etudiant);

        // THEN
        ArgumentCaptor<Etudiant> etudiantCaptor = ArgumentCaptor.forClass(Etudiant.class);
        verify(etudiantRepository).save(etudiantCaptor.capture());
        assertThat(etudiantCaptor.getValue()).isEqualTo(etudiant);
    }

    @Test
    public void test_create_with_null_name_throws_IllegalArgumentException() {
        // GIVEN
        Etudiant etudiant = new Etudiant();
        etudiant.setFirstName(null);
        etudiant.setLastName(LAST_NAME);
        etudiant.setEmail(EMAIL);

        // THEN
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> etudiantService.create(etudiant));
    }

    @Test 
    public void test_create_already_exist_email_throws_IllegalArgumentException() {
        // GIVEN
        Etudiant etudiant = new Etudiant();
        etudiant.setFirstName(FIRST_NAME);
        etudiant.setLastName(LAST_NAME);
        etudiant.setEmail(EMAIL);
        when(etudiantRepository.findByEmail(any())).thenReturn(Optional.of(etudiant));

        // THEN
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> etudiantService.create(etudiant));
    }

    @Test
    public void test_get_all_etudiants() {
        // GIVEN
        Etudiant etudiant1 = new Etudiant();
        etudiant1.setFirstName(FIRST_NAME);
        etudiant1.setLastName(LAST_NAME);
        etudiant1.setEmail(EMAIL);

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setFirstName("Jane");
        etudiant2.setLastName("Smith");
        etudiant2.setEmail("jane.smith@gmail.com");

        when(etudiantRepository.findAll()).thenReturn(java.util.List.of(etudiant1, etudiant2));
        
        // WHEN
        Iterable<Etudiant> etudiants = etudiantService.getAll();

        // THEN
        assertThat(etudiants).containsExactly(etudiant1, etudiant2);
    }

    @Test 
    public void test_get_etudiant() {
        // GIVEN
        Long id = 1L;
        Etudiant etudiant = new Etudiant();
        etudiant.setId(id);
        etudiant.setFirstName(FIRST_NAME);
        etudiant.setLastName(LAST_NAME);
        etudiant.setEmail(EMAIL);
        when(etudiantRepository.findById(id)).thenReturn(Optional.of(etudiant));

        // WHEN
        Optional<Etudiant> result = etudiantService.getById(id);

        // THEN
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(etudiant);
    }

    @Test 
    public void test_get_etudiant_with_non_existing_id_returns_empty() {
        // GIVEN
        Long id = 1L;
        when(etudiantRepository.findById(id)).thenReturn(Optional.empty());

        // WHEN
        Optional<Etudiant> result = etudiantService.getById(id);

        // THEN
        assertThat(result).isNotPresent();
    }

    @Test 
    public void test_update_etudiant() {
        // GIVEN
        Long id = 1L;
        Etudiant etudiant = new Etudiant();
        etudiant.setId(id);
        etudiant.setFirstName(FIRST_NAME);
        etudiant.setLastName(LAST_NAME);
        etudiant.setEmail(EMAIL);
        when(etudiantRepository.findById(id)).thenReturn(Optional.of(etudiant));

        // WHEN
        etudiantService.update(id, etudiant);

        // THEN
        ArgumentCaptor<Etudiant> etudiantCaptor = ArgumentCaptor.forClass(Etudiant.class);
        verify(etudiantRepository).save(etudiantCaptor.capture());
        assertThat(etudiantCaptor.getValue()).isEqualTo(etudiant);
    }

    @Test 
    public void test_update_non_existing_etudiant_throws_IllegalArgumentException() {
        // GIVEN
        Long id = 1L;
        Etudiant etudiant = new Etudiant();
        etudiant.setId(id);
        etudiant.setFirstName(FIRST_NAME);
        etudiant.setLastName(LAST_NAME);
        etudiant.setEmail(EMAIL);
        when(etudiantRepository.findById(id)).thenReturn(Optional.empty());

        // THEN
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> etudiantService.update(id, etudiant));
    }

    @Test
    public void test_update_with_null_name_throws_IllegalArgumentException() {
        // GIVEN
        Long id = 1L;
        Etudiant etudiant = new Etudiant();
        etudiant.setId(id);
        etudiant.setFirstName(null);
        etudiant.setLastName(LAST_NAME);
        etudiant.setEmail(EMAIL);

        // THEN
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> etudiantService.update(id, etudiant));
    }

    @Test 
    public void test_delete_etudiant() {
        // GIVEN
        Long id = 1L;
        Etudiant etudiant = new Etudiant();
        etudiant.setId(id);
        etudiant.setFirstName(FIRST_NAME);
        etudiant.setLastName(LAST_NAME);
        etudiant.setEmail(EMAIL);
        when(etudiantRepository.findById(id)).thenReturn(Optional.of(etudiant));

        // WHEN
        etudiantService.delete(id);

        // THEN
        verify(etudiantRepository).deleteById(id);
    }

    @Test 
    public void test_delete_non_existing_etudiant_throws_IllegalArgumentException() {
        // GIVEN
        Long id = 1L;
        when(etudiantRepository.findById(id)).thenReturn(Optional.empty());

        // THEN
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> etudiantService.delete(id));
    }
}