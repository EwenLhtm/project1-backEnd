package com.openclassrooms.etudiant.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginRequestDTOTest {

    private LoginRequestDTO createDTO() {
        // Création d'un objet LoginRequestDTO avec des valeurs par défaut pour les tests
        LoginRequestDTO dto = new LoginRequestDTO();

        dto.setLogin("jean.dupont");
        dto.setPassword("password123");

        return dto;
    }

    @Test
    void testEqualsSameObject() {
        // Création d'un objet LoginRequestDTO pour le test
        LoginRequestDTO dto = createDTO();

        // Vérification que l'objet est égal à lui-même
        assertEquals(dto, dto);
    }

    @Test
    void testEqualsNull() {
        // Création d'un objet LoginRequestDTO pour le test
        LoginRequestDTO dto = createDTO();

        // Vérification que l'objet n'est pas égal à null
        assertNotEquals(dto, null);
    }

    @Test
    void testEqualsDifferentClass() {
        // Création d'un objet LoginRequestDTO pour le test
        LoginRequestDTO dto = createDTO();

        // Vérification que l'objet n'est pas égal à un objet d'une classe différente
        assertNotEquals(dto, "jean.dupont");
    }

    @Test
    void testEqualsSameValues() {
        // Création de deux objets LoginRequestDTO avec les mêmes valeurs pour le test
        LoginRequestDTO dto1 = createDTO();
        LoginRequestDTO dto2 = createDTO();

        // Vérification que les deux objets sont égaux
        assertEquals(dto1, dto2);
    }

    @Test
    void testEqualsDifferentLogin() {
        // Création de deux objets LoginRequestDTO avec des valeurs différentes pour le test
        LoginRequestDTO dto1 = createDTO();
        LoginRequestDTO dto2 = createDTO();

        // Modification de la valeur du login pour le deuxième objet
        dto2.setLogin("pierre.martin");

        // Vérification que les deux objets ne sont pas égaux
        assertNotEquals(dto1, dto2);
    }

    @Test
    void testEqualsDifferentPassword() {
        // Création de deux objets LoginRequestDTO avec des valeurs différentes pour le test
        LoginRequestDTO dto1 = createDTO();
        LoginRequestDTO dto2 = createDTO();

        // Modification de la valeur du mot de passe pour le deuxième objet
        dto2.setPassword("differentPassword");

        // Vérification que les deux objets ne sont pas égaux
        assertNotEquals(dto1, dto2);
    }

    @Test
    void testHashCodeSameValues() {
        // Création de deux objets LoginRequestDTO avec les mêmes valeurs pour le test
        LoginRequestDTO dto1 = createDTO();
        LoginRequestDTO dto2 = createDTO();

        // Vérification que les deux objets ont le même code de hachage
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testHashCodeDifferentValues() {
        // Création de deux objets LoginRequestDTO avec des valeurs différentes pour le test
        LoginRequestDTO dto1 = createDTO();
        LoginRequestDTO dto2 = createDTO();

        // Modification de la valeur du login pour le deuxième objet
        dto2.setLogin("different.login");

        // Vérification que les deux objets ont des codes de hachage différents
        assertNotEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToString() {
        // Création d'un objet LoginRequestDTO pour le test
        LoginRequestDTO dto = createDTO();

        // Appel de la méthode toString() pour obtenir la représentation sous forme de chaîne de caractères
        String result = dto.toString();

        // Vérification que la représentation sous forme de chaîne de caractères n'est pas nulle et contient les valeurs attendues
        assertNotNull(result);
        assertTrue(result.contains("jean.dupont"));
        assertTrue(result.contains("password123"));
    }

    @Test
    void testCanEqual() {
        // Création de deux objets LoginRequestDTO pour le test
        LoginRequestDTO dto1 = createDTO();
        LoginRequestDTO dto2 = createDTO();

        // Vérification que le premier objet peut être égal au deuxième objet
        assertTrue(dto1.canEqual(dto2));
        assertFalse(dto1.canEqual(null));
        assertFalse(dto1.canEqual("jean.dupont"));
    }

    @Test
    void testGettersAndSetters() {
        // Création d'un objet LoginRequestDTO pour le test
        LoginRequestDTO dto = new LoginRequestDTO();

        // Vérification des getters et setters pour les propriétés login et password
        dto.setLogin("jean.dupont");
        dto.setPassword("password123");

        // Vérification que les valeurs définies sont correctement récupérées par les getters
        assertEquals("jean.dupont", dto.getLogin());
        assertEquals("password123", dto.getPassword());
    }
}