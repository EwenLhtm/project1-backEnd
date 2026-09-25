package com.openclassrooms.etudiant.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EtudiantRequestDTOTest {

    private EtudiantRequestDTO createDTO() {
        // Création d'un objet EtudiantRequestDTO avec des valeurs par défaut pour les tests
        EtudiantRequestDTO dto = new EtudiantRequestDTO();

        dto.setFirstName("Jean");
        dto.setLastName("Dupont");
        dto.setEmail("jean.dupont@example.com");

        return dto;
    }

    @Test
    void testEqualsSameObject() {
        // Création d'un objet EtudiantRequestDTO pour le test
        EtudiantRequestDTO dto = createDTO();

        // Vérification que l'objet est égal à lui-même
        assertEquals(dto, dto);
    }

    @Test
    void testEqualsNull() {
        // Création d'un objet EtudiantRequestDTO pour le test
        EtudiantRequestDTO dto = createDTO();

        // Vérification que l'objet n'est pas égal à null
        assertNotEquals(dto, null);
    }

    @Test
    void testEqualsDifferentClass() {
        // Création d'un objet EtudiantRequestDTO pour le test
        EtudiantRequestDTO dto = createDTO();

        // Vérification que l'objet n'est pas égal à un objet d'une classe différente
        assertNotEquals(dto, "Jean");
    }

    @Test
    void testEqualsSameValues() {
        // Création de deux objets EtudiantRequestDTO avec les mêmes valeurs pour le test
        EtudiantRequestDTO dto1 = createDTO();
        EtudiantRequestDTO dto2 = createDTO();

        // Vérification que les deux objets sont égaux
        assertEquals(dto1, dto2);
    }

    @Test
    void testEqualsDifferentFirstName() {
        // Création de deux objets EtudiantRequestDTO avec des valeurs différentes pour le test
        EtudiantRequestDTO dto1 = createDTO();
        EtudiantRequestDTO dto2 = createDTO();

        // Modification du prénom du deuxième objet pour le test
        dto2.setFirstName("Pierre");

        // Vérification que les deux objets ne sont pas égaux
        assertNotEquals(dto1, dto2);
    }

    @Test
    void testEqualsDifferentLastName() {
        // création de deux objets EtudiantRequestDTO avec des valeurs différentes pour le test
        EtudiantRequestDTO dto1 = createDTO();
        EtudiantRequestDTO dto2 = createDTO();

        // Modification du nom de famille du deuxième objet pour le test
        dto2.setLastName("Martin");

        // Vérification que les deux objets ne sont pas égaux
        assertNotEquals(dto1, dto2);
    }

    @Test
    void testEqualsDifferentEmail() {
        // Création de deux objets EtudiantRequestDTO avec des valeurs différentes pour le test
        EtudiantRequestDTO dto1 = createDTO();
        EtudiantRequestDTO dto2 = createDTO();

        // Modification de l'email du deuxième objet pour le test
        dto2.setEmail("pierre.martin@example.com");

        // Vérification que les deux objets ne sont pas égaux
        assertNotEquals(dto1, dto2);
    }

    @Test
    void testHashCodeSameValues() {
        // Création de deux objets EtudiantRequestDTO avec les mêmes valeurs pour le test
        EtudiantRequestDTO dto1 = createDTO();
        EtudiantRequestDTO dto2 = createDTO();

        // Vérification que les deux objets ont le même code de hachage
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testHashCodeDifferentValues() {
        // Création de deux objets EtudiantRequestDTO avec des valeurs différentes pour le test
        EtudiantRequestDTO dto1 = createDTO();
        EtudiantRequestDTO dto2 = createDTO();

        // Modification de l'email du deuxième objet pour le test
        dto2.setEmail("different@example.com");

        // Vérification que les deux objets ont des codes de hachage différents
        assertNotEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToString() {
        // Création d'un objet EtudiantRequestDTO pour le test
        EtudiantRequestDTO dto = createDTO();

        // Appel de la méthode toString() pour obtenir la représentation sous forme de chaîne de caractères
        String result = dto.toString();

        // Vérification que la représentation sous forme de chaîne de caractères n'est pas nulle et contient les valeurs attendues
        assertNotNull(result);
        assertTrue(result.contains("Jean"));
        assertTrue(result.contains("Dupont"));
        assertTrue(result.contains("jean.dupont@example.com"));
    }

    @Test
    void testCanEqual() {
        // Création de deux objets EtudiantRequestDTO pour le test
        EtudiantRequestDTO dto1 = createDTO();
        EtudiantRequestDTO dto2 = createDTO();

        // Vérification que le premier objet peut être égal au deuxième objet
        assertTrue(dto1.canEqual(dto2));
        assertFalse(dto1.canEqual(null));
        assertFalse(dto1.canEqual("Jean"));
    }
}