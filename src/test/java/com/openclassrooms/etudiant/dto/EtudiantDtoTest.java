package com.openclassrooms.etudiant.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EtudiantRequestDTOTest {

    private EtudiantRequestDTO createDTO() {
        EtudiantRequestDTO dto = new EtudiantRequestDTO();

        dto.setFirstName("Jean");
        dto.setLastName("Dupont");
        dto.setEmail("jean.dupont@example.com");

        return dto;
    }

    @Test
    void testEqualsSameObject() {
        EtudiantRequestDTO dto = createDTO();

        assertEquals(dto, dto);
    }

    @Test
    void testEqualsNull() {
        EtudiantRequestDTO dto = createDTO();

        assertNotEquals(dto, null);
    }

    @Test
    void testEqualsDifferentClass() {
        EtudiantRequestDTO dto = createDTO();

        assertNotEquals(dto, "Jean");
    }

    @Test
    void testEqualsSameValues() {
        EtudiantRequestDTO dto1 = createDTO();
        EtudiantRequestDTO dto2 = createDTO();

        assertEquals(dto1, dto2);
    }

    @Test
    void testEqualsDifferentFirstName() {
        EtudiantRequestDTO dto1 = createDTO();
        EtudiantRequestDTO dto2 = createDTO();

        dto2.setFirstName("Pierre");

        assertNotEquals(dto1, dto2);
    }

    @Test
    void testEqualsDifferentLastName() {
        EtudiantRequestDTO dto1 = createDTO();
        EtudiantRequestDTO dto2 = createDTO();

        dto2.setLastName("Martin");

        assertNotEquals(dto1, dto2);
    }

    @Test
    void testEqualsDifferentEmail() {
        EtudiantRequestDTO dto1 = createDTO();
        EtudiantRequestDTO dto2 = createDTO();

        dto2.setEmail("pierre.martin@example.com");

        assertNotEquals(dto1, dto2);
    }

    @Test
    void testHashCodeSameValues() {
        EtudiantRequestDTO dto1 = createDTO();
        EtudiantRequestDTO dto2 = createDTO();

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testHashCodeDifferentValues() {
        EtudiantRequestDTO dto1 = createDTO();
        EtudiantRequestDTO dto2 = createDTO();

        dto2.setEmail("different@example.com");

        assertNotEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToString() {
        EtudiantRequestDTO dto = createDTO();

        String result = dto.toString();

        assertNotNull(result);
        assertTrue(result.contains("Jean"));
        assertTrue(result.contains("Dupont"));
        assertTrue(result.contains("jean.dupont@example.com"));
    }

    @Test
    void testCanEqual() {
        EtudiantRequestDTO dto1 = createDTO();
        EtudiantRequestDTO dto2 = createDTO();

        assertTrue(dto1.canEqual(dto2));
        assertFalse(dto1.canEqual(null));
        assertFalse(dto1.canEqual("Jean"));
    }
}