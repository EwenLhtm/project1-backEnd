package com.openclassrooms.etudiant.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginRequestDTOTest {

    private LoginRequestDTO createDTO() {
        LoginRequestDTO dto = new LoginRequestDTO();

        dto.setLogin("jean.dupont");
        dto.setPassword("password123");

        return dto;
    }

    @Test
    void testEqualsSameObject() {
        LoginRequestDTO dto = createDTO();

        assertEquals(dto, dto);
    }

    @Test
    void testEqualsNull() {
        LoginRequestDTO dto = createDTO();

        assertNotEquals(dto, null);
    }

    @Test
    void testEqualsDifferentClass() {
        LoginRequestDTO dto = createDTO();

        assertNotEquals(dto, "jean.dupont");
    }

    @Test
    void testEqualsSameValues() {
        LoginRequestDTO dto1 = createDTO();
        LoginRequestDTO dto2 = createDTO();

        assertEquals(dto1, dto2);
    }

    @Test
    void testEqualsDifferentLogin() {
        LoginRequestDTO dto1 = createDTO();
        LoginRequestDTO dto2 = createDTO();

        dto2.setLogin("pierre.martin");

        assertNotEquals(dto1, dto2);
    }

    @Test
    void testEqualsDifferentPassword() {
        LoginRequestDTO dto1 = createDTO();
        LoginRequestDTO dto2 = createDTO();

        dto2.setPassword("differentPassword");

        assertNotEquals(dto1, dto2);
    }

    @Test
    void testHashCodeSameValues() {
        LoginRequestDTO dto1 = createDTO();
        LoginRequestDTO dto2 = createDTO();

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testHashCodeDifferentValues() {
        LoginRequestDTO dto1 = createDTO();
        LoginRequestDTO dto2 = createDTO();

        dto2.setLogin("different.login");

        assertNotEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToString() {
        LoginRequestDTO dto = createDTO();

        String result = dto.toString();

        assertNotNull(result);
        assertTrue(result.contains("jean.dupont"));
        assertTrue(result.contains("password123"));
    }

    @Test
    void testCanEqual() {
        LoginRequestDTO dto1 = createDTO();
        LoginRequestDTO dto2 = createDTO();

        assertTrue(dto1.canEqual(dto2));
        assertFalse(dto1.canEqual(null));
        assertFalse(dto1.canEqual("jean.dupont"));
    }

    @Test
    void testGettersAndSetters() {
        LoginRequestDTO dto = new LoginRequestDTO();

        dto.setLogin("jean.dupont");
        dto.setPassword("password123");

        assertEquals("jean.dupont", dto.getLogin());
        assertEquals("password123", dto.getPassword());
    }
}