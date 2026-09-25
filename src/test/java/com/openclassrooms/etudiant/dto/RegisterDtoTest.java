package com.openclassrooms.etudiant.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterDTOTest {

    private RegisterDTO createDTO() {
        // Création d'un objet RegisterDTO avec des valeurs par défaut pour les tests
        RegisterDTO dto = new RegisterDTO();

        dto.setFirstName("Jean");
        dto.setLastName("Dupont");
        dto.setLogin("jean.dupont");
        dto.setPassword("password123");

        return dto;
    }

    @Test
    void testEqualsSameObject() {
        // Création d'un objet RegisterDTO pour le test
        RegisterDTO dto = createDTO();

        // Vérification que l'objet est égal à lui-même
        assertEquals(dto, dto);
    }

    @Test
    void testEqualsNull() {
        // Création d'un objet RegisterDTO pour le test
        RegisterDTO dto = createDTO();

        // Vérification que l'objet n'est pas égal à null
        assertNotEquals(dto, null);
    }

    @Test
    void testEqualsDifferentClass() {
        // Création d'un objet RegisterDTO pour le test
        RegisterDTO dto = createDTO();

        // Vérification que l'objet n'est pas égal à un objet d'une classe différente
        assertNotEquals(dto, "Jean");
    }

    @Test
    void testEqualsSameValues() {
        // Création de deux objets RegisterDTO avec les mêmes valeurs pour le test
        RegisterDTO dto1 = createDTO();
        RegisterDTO dto2 = createDTO();

        // Vérification que les deux objets sont égaux
        assertEquals(dto1, dto2);
    }

    @Test
    void testEqualsDifferentFirstName() {
        // Création de deux objets RegisterDTO avec des valeurs différentes pour le test
        RegisterDTO dto1 = createDTO();
        RegisterDTO dto2 = createDTO();

        // Modification du prénom du deuxième objet pour le test
        dto2.setFirstName("Pierre");

        // Vérification que les deux objets ne sont pas égaux
        assertNotEquals(dto1, dto2);
    }

    @Test
    void testEqualsDifferentLastName() {
        // Création de deux objets RegisterDTO avec des valeurs différentes pour le test
        RegisterDTO dto1 = createDTO();
        RegisterDTO dto2 = createDTO();

        // Modification du nom de famille du deuxième objet pour le test
        dto2.setLastName("Martin");

        // Vérification que les deux objets ne sont pas égaux
        assertNotEquals(dto1, dto2);
    }

    @Test
    void testEqualsDifferentLogin() {
        // Création de deux objets RegisterDTO avec des valeurs différentes pour le test
        RegisterDTO dto1 = createDTO();
        RegisterDTO dto2 = createDTO();

        // Modification du login du deuxième objet pour le test
        dto2.setLogin("pierre.martin");

        // Vérification que les deux objets ne sont pas égaux
        assertNotEquals(dto1, dto2);
    }

    @Test
    void testEqualsDifferentPassword() {
        // Création de deux objets RegisterDTO avec des valeurs différentes pour le test
        RegisterDTO dto1 = createDTO();
        RegisterDTO dto2 = createDTO();

        // Modification du mot de passe du deuxième objet pour le test
        dto2.setPassword("differentPassword");

        // Vérification que les deux objets ne sont pas égaux
        assertNotEquals(dto1, dto2);
    }

    @Test
    void testHashCodeSameValues() {
        // Création de deux objets RegisterDTO avec les mêmes valeurs pour le test
        RegisterDTO dto1 = createDTO();
        RegisterDTO dto2 = createDTO();

        // Vérification que les deux objets ont le même code de hachage
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testHashCodeDifferentValues() {
        // Création de deux objets RegisterDTO avec des valeurs différentes pour le test
        RegisterDTO dto1 = createDTO();
        RegisterDTO dto2 = createDTO();

        // Modification du login du deuxième objet pour le test 
        dto2.setLogin("different.login");

        // Vérification que les deux objets ont des codes de hachage différents
        assertNotEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToString() {
        // Création d'un objet RegisterDTO pour le test
        RegisterDTO dto = createDTO();

        // Appel de la méthode toString() pour obtenir la représentation sous forme de chaîne de caractères
        String result = dto.toString();

        // Vérification que la représentation sous forme de chaîne de caractères n'est pas nulle et contient les valeurs attendues
        assertNotNull(result);
        assertTrue(result.contains("Jean"));
        assertTrue(result.contains("Dupont"));
        assertTrue(result.contains("jean.dupont"));
        assertTrue(result.contains("password123"));
    }

    @Test
    void testCanEqual() {
        // Création de deux objets RegisterDTO pour le test
        RegisterDTO dto1 = createDTO();
        RegisterDTO dto2 = createDTO();

        // Vérification que le premier objet peut être égal au deuxième objet
        assertTrue(dto1.canEqual(dto2));
        assertFalse(dto1.canEqual(null));
        assertFalse(dto1.canEqual("Jean"));
    }

    @Test
    void testGettersAndSetters() {
        // Création d'un objet RegisterDTO pour le test
        RegisterDTO dto = new RegisterDTO();

        // Utilisation des setters pour définir les valeurs
        dto.setFirstName("Jean");
        dto.setLastName("Dupont");
        dto.setLogin("jean.dupont");
        dto.setPassword("password123");

        // Vérification que les getters retournent les valeurs attendues
        assertEquals("Jean", dto.getFirstName());
        assertEquals("Dupont", dto.getLastName());
        assertEquals("jean.dupont", dto.getLogin());
        assertEquals("password123", dto.getPassword());
    }
}