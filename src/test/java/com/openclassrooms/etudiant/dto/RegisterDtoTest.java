package com.openclassrooms.etudiant.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterDTOTest {

    private RegisterDTO createDTO() {
        RegisterDTO dto = new RegisterDTO();

        dto.setFirstName("Jean");
        dto.setLastName("Dupont");
        dto.setLogin("jean.dupont");
        dto.setPassword("password123");

        return dto;
    }

    @Test
    void testEqualsSameObject() {
        RegisterDTO dto = createDTO();

        assertEquals(dto, dto);
    }

    @Test
    void testEqualsNull() {
        RegisterDTO dto = createDTO();

        assertNotEquals(dto, null);
    }

    @Test
    void testEqualsDifferentClass() {
        RegisterDTO dto = createDTO();

        assertNotEquals(dto, "Jean");
    }

    @Test
    void testEqualsSameValues() {
        RegisterDTO dto1 = createDTO();
        RegisterDTO dto2 = createDTO();

        assertEquals(dto1, dto2);
    }

    @Test
    void testEqualsDifferentFirstName() {
        RegisterDTO dto1 = createDTO();
        RegisterDTO dto2 = createDTO();

        dto2.setFirstName("Pierre");

        assertNotEquals(dto1, dto2);
    }

    @Test
    void testEqualsDifferentLastName() {
        RegisterDTO dto1 = createDTO();
        RegisterDTO dto2 = createDTO();

        dto2.setLastName("Martin");

        assertNotEquals(dto1, dto2);
    }

    @Test
    void testEqualsDifferentLogin() {
        RegisterDTO dto1 = createDTO();
        RegisterDTO dto2 = createDTO();

        dto2.setLogin("pierre.martin");

        assertNotEquals(dto1, dto2);
    }

    @Test
    void testEqualsDifferentPassword() {
        RegisterDTO dto1 = createDTO();
        RegisterDTO dto2 = createDTO();

        dto2.setPassword("differentPassword");

        assertNotEquals(dto1, dto2);
    }

    @Test
    void testHashCodeSameValues() {
        RegisterDTO dto1 = createDTO();
        RegisterDTO dto2 = createDTO();

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testHashCodeDifferentValues() {
        RegisterDTO dto1 = createDTO();
        RegisterDTO dto2 = createDTO();

        dto2.setLogin("different.login");

        assertNotEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToString() {
        RegisterDTO dto = createDTO();

        String result = dto.toString();

        assertNotNull(result);
        assertTrue(result.contains("Jean"));
        assertTrue(result.contains("Dupont"));
        assertTrue(result.contains("jean.dupont"));
        assertTrue(result.contains("password123"));
    }

    @Test
    void testCanEqual() {
        RegisterDTO dto1 = createDTO();
        RegisterDTO dto2 = createDTO();

        assertTrue(dto1.canEqual(dto2));
        assertFalse(dto1.canEqual(null));
        assertFalse(dto1.canEqual("Jean"));
    }

    @Test
    void testGettersAndSetters() {
        RegisterDTO dto = new RegisterDTO();

        dto.setFirstName("Jean");
        dto.setLastName("Dupont");
        dto.setLogin("jean.dupont");
        dto.setPassword("password123");

        assertEquals("Jean", dto.getFirstName());
        assertEquals("Dupont", dto.getLastName());
        assertEquals("jean.dupont", dto.getLogin());
        assertEquals("password123", dto.getPassword());
    }
}