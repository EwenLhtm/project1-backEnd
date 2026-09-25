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
        // Test de création d'un étudiant avec le constructeur sans arguments
        Etudiant etudiant = new Etudiant();

        // Vérification que l'objet est créé et que ses propriétés sont nulles
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
        // Test de création d'un étudiant avec le constructeur avec tous les arguments
        Long id = 1L;
        String firstName = "Jean";
        String lastName = "Dupont";
        String email = "jean.dupont@example.com";
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();

        // Création de l'étudiant avec le constructeur avec tous les arguments
        Etudiant etudiant = new Etudiant(
                id,
                firstName,
                lastName,
                email,
                createdAt,
                updatedAt
        );

        // Vérification que l'objet est créé et que ses propriétés sont correctement initialisées
        assertEquals(id, etudiant.getId());
        assertEquals(firstName, etudiant.getFirstName());
        assertEquals(lastName, etudiant.getLastName());
        assertEquals(email, etudiant.getEmail());
        assertEquals(createdAt, etudiant.getCreated_at());
        assertEquals(updatedAt, etudiant.getUpdated_at());
    }

    @Test
    void shouldSetAndGetProperties() {
        // Test de la définition et de la récupération des propriétés d'un étudiant
        Etudiant etudiant = new Etudiant();

        // Définition des propriétés de l'étudiant
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();

        // Vérification que les propriétés sont correctement définies et récupérées
        etudiant.setId(1L);
        etudiant.setFirstName("Jean");
        etudiant.setLastName("Dupont");
        etudiant.setEmail("jean.dupont@example.com");
        etudiant.setCreated_at(createdAt);
        etudiant.setUpdated_at(updatedAt);

        // Vérification que les propriétés sont correctement définies et récupérées
        assertEquals(1L, etudiant.getId());
        assertEquals("Jean", etudiant.getFirstName());
        assertEquals("Dupont", etudiant.getLastName());
        assertEquals("jean.dupont@example.com", etudiant.getEmail());
        assertEquals(createdAt, etudiant.getCreated_at());
        assertEquals(updatedAt, etudiant.getUpdated_at());
    }

    @Test
    void shouldBeValidWhenRequiredFieldsAreFilled() {
        // Test de validation d'un étudiant lorsque les champs requis sont remplis
        Etudiant etudiant = new Etudiant();
        etudiant.setFirstName("Jean");
        etudiant.setLastName("Dupont");
        etudiant.setEmail("jean.dupont@example.com");

        // Vérification que l'étudiant est valide lorsque les champs requis sont remplis
        Set<ConstraintViolation<Etudiant>> violations =
                validator.validate(etudiant);

        // Vérification que l'ensemble des violations est vide, ce qui signifie que l'étudiant est valide
        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldBeInvalidWhenFirstNameIsBlank() {
        // Test de validation d'un étudiant lorsque le prénom est vide
        Etudiant etudiant = new Etudiant();
        etudiant.setFirstName("");
        etudiant.setLastName("Dupont");
        etudiant.setEmail("jean.dupont@example.com");

        // Vérification que l'étudiant est invalide lorsque le prénom est vide
        Set<ConstraintViolation<Etudiant>> violations =
                validator.validate(etudiant);

        // Vérification que l'ensemble des violations contient une violation pour le champ firstName
        assertEquals(1, violations.size());
        assertEquals("firstName", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    void shouldBeInvalidWhenLastNameIsBlank() {
        // Test de validation d'un étudiant lorsque le nom de famille est vide
        Etudiant etudiant = new Etudiant();
        etudiant.setFirstName("Jean");
        etudiant.setLastName("");
        etudiant.setEmail("jean.dupont@example.com");

        // Vérification que l'étudiant est invalide lorsque le nom de famille est vide
        Set<ConstraintViolation<Etudiant>> violations =
                validator.validate(etudiant);

        // Vérification que l'ensemble des violations contient une violation pour le champ lastName
        assertEquals(1, violations.size());
        assertEquals("lastName", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    void shouldBeInvalidWhenEmailIsBlank() {
        // Test de validation d'un étudiant lorsque l'email est vide
        Etudiant etudiant = new Etudiant();
        etudiant.setFirstName("Jean");
        etudiant.setLastName("Dupont");
        etudiant.setEmail("");

        // Vérification que l'étudiant est invalide lorsque l'email est vide
        Set<ConstraintViolation<Etudiant>> violations =
                validator.validate(etudiant);

        // Vérification que l'ensemble des violations contient une violation pour le champ email
        assertEquals(1, violations.size());
        assertEquals("email", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    void shouldBeInvalidWhenRequiredFieldsAreNull() {
        // Test de validation d'un étudiant lorsque les champs requis sont null
        Etudiant etudiant = new Etudiant();

        // Vérification que l'étudiant est invalide lorsque les champs requis sont null
        Set<ConstraintViolation<Etudiant>> violations =
                validator.validate(etudiant);

        // Vérification que l'ensemble des violations contient des violations pour les champs firstName, lastName et email
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
        // Test de la méthode equals() et hashCode() de la classe Etudiant
        LocalDateTime dateCreation = LocalDateTime.of(2024, 1, 1, 10, 0);
        LocalDateTime dateModification = LocalDateTime.of(2024, 1, 2, 10, 0);

        // Création de trois objets Etudiant pour le test
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
        // Test de la méthode equals() avec des objets Etudiant ayant des champs différents
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

        // Vérification que les objets avec des champs différents ne sont pas égaux
        assertNotEquals(reference, differentId);
        assertNotEquals(reference, differentNom);
        assertNotEquals(reference, differentPrenom);
        assertNotEquals(reference, differentEmail);
        assertNotEquals(reference, differentDateCreation);
        assertNotEquals(reference, differentDateModification);
    }

    @Test
    void testToString() {
        // Test de la méthode toString() de la classe Etudiant
        Etudiant etudiant = new Etudiant(1L, "Jean", "Dupont", "jean.dupont@example.com", LocalDateTime.now(), LocalDateTime.now());
        String expected = "Etudiant(id=1, firstName=Jean, lastName=Dupont, email=jean.dupont@example.com, created_at=" + etudiant.getCreated_at() + ", updated_at=" + etudiant.getUpdated_at() + ")";

        // Vérification que la représentation sous forme de chaîne de caractères de l'objet est correcte
        assertEquals(expected, etudiant.toString());
    }
}