package com.openclassrooms.etudiant.entities;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EtudiantTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldCreateEtudiantWithNoArgsConstructor() {
        Etudiant etudiant = new Etudiant();

        assertNotNull(etudiant);
        assertNull(etudiant.getId());
        assertNull(etudiant.getFirstName());
        assertNull(etudiant.getLastName());
        assertNull(etudiant.getEmail());
        assertNull(etudiant.getCreated_at());
        assertNull(etudiant.getUpdated_at());
    }

    @Test
    void shouldCreateEtudiantWithAllArgsConstructor() {
        Long id = 1L;
        String firstName = "Jean";
        String lastName = "Dupont";
        String email = "jean.dupont@example.com";
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();

        Etudiant etudiant = new Etudiant(
                id,
                firstName,
                lastName,
                email,
                createdAt,
                updatedAt
        );

        assertEquals(id, etudiant.getId());
        assertEquals(firstName, etudiant.getFirstName());
        assertEquals(lastName, etudiant.getLastName());
        assertEquals(email, etudiant.getEmail());
        assertEquals(createdAt, etudiant.getCreated_at());
        assertEquals(updatedAt, etudiant.getUpdated_at());
    }

    @Test
    void shouldSetAndGetProperties() {
        Etudiant etudiant = new Etudiant();

        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();

        etudiant.setId(1L);
        etudiant.setFirstName("Jean");
        etudiant.setLastName("Dupont");
        etudiant.setEmail("jean.dupont@example.com");
        etudiant.setCreated_at(createdAt);
        etudiant.setUpdated_at(updatedAt);

        assertEquals(1L, etudiant.getId());
        assertEquals("Jean", etudiant.getFirstName());
        assertEquals("Dupont", etudiant.getLastName());
        assertEquals("jean.dupont@example.com", etudiant.getEmail());
        assertEquals(createdAt, etudiant.getCreated_at());
        assertEquals(updatedAt, etudiant.getUpdated_at());
    }

    @Test
    void shouldBeValidWhenRequiredFieldsAreFilled() {
        Etudiant etudiant = new Etudiant();
        etudiant.setFirstName("Jean");
        etudiant.setLastName("Dupont");
        etudiant.setEmail("jean.dupont@example.com");

        Set<ConstraintViolation<Etudiant>> violations =
                validator.validate(etudiant);

        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldBeInvalidWhenFirstNameIsBlank() {
        Etudiant etudiant = new Etudiant();
        etudiant.setFirstName("");
        etudiant.setLastName("Dupont");
        etudiant.setEmail("jean.dupont@example.com");

        Set<ConstraintViolation<Etudiant>> violations =
                validator.validate(etudiant);

        assertEquals(1, violations.size());
        assertEquals("firstName", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    void shouldBeInvalidWhenLastNameIsBlank() {
        Etudiant etudiant = new Etudiant();
        etudiant.setFirstName("Jean");
        etudiant.setLastName("");
        etudiant.setEmail("jean.dupont@example.com");

        Set<ConstraintViolation<Etudiant>> violations =
                validator.validate(etudiant);

        assertEquals(1, violations.size());
        assertEquals("lastName", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    void shouldBeInvalidWhenEmailIsBlank() {
        Etudiant etudiant = new Etudiant();
        etudiant.setFirstName("Jean");
        etudiant.setLastName("Dupont");
        etudiant.setEmail("");

        Set<ConstraintViolation<Etudiant>> violations =
                validator.validate(etudiant);

        assertEquals(1, violations.size());
        assertEquals("email", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    void shouldBeInvalidWhenRequiredFieldsAreNull() {
        Etudiant etudiant = new Etudiant();

        Set<ConstraintViolation<Etudiant>> violations =
                validator.validate(etudiant);

        assertEquals(3, violations.size());

        assertTrue(
                violations.stream()
                        .anyMatch(v -> v.getPropertyPath().toString().equals("firstName"))
        );

        assertTrue(
                violations.stream()
                        .anyMatch(v -> v.getPropertyPath().toString().equals("lastName"))
        );

        assertTrue(
                violations.stream()
                        .anyMatch(v -> v.getPropertyPath().toString().equals("email"))
        );
    }
    
    @Test
    void testEqualsAndHashCode() {
        LocalDateTime dateCreation = LocalDateTime.of(2024, 1, 1, 10, 0);
        LocalDateTime dateModification = LocalDateTime.of(2024, 1, 2, 10, 0);

        Etudiant etudiant1 = new Etudiant(
                1L,
                "Jean",
                "Dupont",
                "jean.dupont@example.com",
                dateCreation,
                dateModification
        );

        Etudiant etudiant2 = new Etudiant(
                1L,
                "Jean",
                "Dupont",
                "jean.dupont@example.com",
                dateCreation,
                dateModification
        );

        Etudiant etudiant3 = new Etudiant(
                2L,
                "Pierre",
                "Martin",
                "pierre.martin@example.com",
                dateCreation,
                dateModification
        );

        // Même objet -> couvre généralement "this == o"
        assertEquals(etudiant1, etudiant1);

        // Objets différents mais avec les mêmes valeurs
        assertEquals(etudiant1, etudiant2);
        assertEquals(etudiant2, etudiant1);

        // Objets différents
        assertNotEquals(etudiant1, etudiant3);
        assertNotEquals(etudiant2, etudiant3);

        // Comparaison avec null
        assertNotEquals(etudiant1, null);

        // Comparaison avec un autre type
        assertNotEquals(etudiant1, "Jean");

        // HashCode : deux objets égaux doivent avoir le même hashCode
        assertEquals(etudiant1.hashCode(), etudiant2.hashCode());

        // Objets avec des IDs différents
        assertNotEquals(etudiant1.hashCode(), etudiant3.hashCode());
        assertNotEquals(etudiant2.hashCode(), etudiant3.hashCode());
    }

    @Test
    void testEqualsWithDifferentFields() {
        LocalDateTime dateCreation = LocalDateTime.of(2024, 1, 1, 10, 0);
        LocalDateTime dateModification = LocalDateTime.of(2024, 1, 2, 10, 0);

        Etudiant reference = new Etudiant(
                1L,
                "Jean",
                "Dupont",
                "jean.dupont@example.com",
                dateCreation,
                dateModification
        );

        // ID différent
        Etudiant differentId = new Etudiant(
                2L,
                "Jean",
                "Dupont",
                "jean.dupont@example.com",
                dateCreation,
                dateModification
        );

        // Nom différent
        Etudiant differentNom = new Etudiant(
                1L,
                "Jean",
                "Martin",
                "jean.dupont@example.com",
                dateCreation,
                dateModification
        );

        // Prénom différent
        Etudiant differentPrenom = new Etudiant(
                1L,
                "Pierre",
                "Dupont",
                "jean.dupont@example.com",
                dateCreation,
                dateModification
        );

        // Email différent
        Etudiant differentEmail = new Etudiant(
                1L,
                "Jean",
                "Dupont",
                "pierre.martin@example.com",
                dateCreation,
                dateModification
        );

        // Date de création différente
        Etudiant differentDateCreation = new Etudiant(
                1L,
                "Jean",
                "Dupont",
                "jean.dupont@example.com",
                dateCreation.plusDays(1),
                dateModification
        );

        // Date de modification différente
        Etudiant differentDateModification = new Etudiant(
                1L,
                "Jean",
                "Dupont",
                "jean.dupont@example.com",
                dateCreation,
                dateModification.plusDays(1)
        );

        assertNotEquals(reference, differentId);
        assertNotEquals(reference, differentNom);
        assertNotEquals(reference, differentPrenom);
        assertNotEquals(reference, differentEmail);
        assertNotEquals(reference, differentDateCreation);
        assertNotEquals(reference, differentDateModification);
    }

    @Test
    void testToString() {
        Etudiant etudiant = new Etudiant(1L, "Jean", "Dupont", "jean.dupont@example.com", LocalDateTime.now(), LocalDateTime.now());
        String expected = "Etudiant(id=1, firstName=Jean, lastName=Dupont, email=jean.dupont@example.com, created_at=" + etudiant.getCreated_at() + ", updated_at=" + etudiant.getUpdated_at() + ")";

        assertEquals(expected, etudiant.toString());
    }
}