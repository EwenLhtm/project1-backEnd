package com.openclassrooms.etudiant.entities;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User createUser() {
        return new User(
                1L,
                "Jean",
                "Dupont",
                "jean.dupont",
                "password123",
                LocalDateTime.of(2024, 1, 1, 10, 0),
                LocalDateTime.of(2024, 1, 2, 10, 0)
        );
    }

    @Test
    void testAllArgsConstructor() {
        LocalDateTime createdAt = LocalDateTime.of(2024, 1, 1, 10, 0);
        LocalDateTime updatedAt = LocalDateTime.of(2024, 1, 2, 10, 0);

        User user = new User(
                1L,
                "Jean",
                "Dupont",
                "jean.dupont",
                "password123",
                createdAt,
                updatedAt
        );

        assertEquals(1L, user.getId());
        assertEquals("Jean", user.getFirstName());
        assertEquals("Dupont", user.getLastName());
        assertEquals("jean.dupont", user.getLogin());
        assertEquals("password123", user.getPassword());
        assertEquals(createdAt, user.getCreated_at());
        assertEquals(updatedAt, user.getUpdated_at());
    }

    @Test
    void testSetId() {
        User user = new User();

        user.setId(10L);

        assertEquals(10L, user.getId());
    }

    @Test
    void testSetCreatedAt() {
        User user = new User();
        LocalDateTime date = LocalDateTime.of(2024, 1, 1, 10, 0);

        user.setCreated_at(date);

        assertEquals(date, user.getCreated_at());
    }

    @Test
    void testSetUpdatedAt() {
        User user = new User();
        LocalDateTime date = LocalDateTime.of(2024, 1, 2, 10, 0);

        user.setUpdated_at(date);

        assertEquals(date, user.getUpdated_at());
    }

    @Test
    void testGetUsername() {
        User user = createUser();

        assertEquals("jean.dupont", user.getUsername());
    }

    @Test
    void testGetId() {
        User user = createUser();

        assertEquals(1L, user.getId());
    }

    @Test
    void testGetFirstName() {
        User user = createUser();

        assertEquals("Jean", user.getFirstName());
    }

    @Test
    void testGetLastName() {
        User user = createUser();

        assertEquals("Dupont", user.getLastName());
    }

    @Test
    void testGetCreatedAt() {
        User user = createUser();

        assertEquals(
                LocalDateTime.of(2024, 1, 1, 10, 0),
                user.getCreated_at()
        );
    }

    @Test
    void testGetUpdatedAt() {
        User user = createUser();

        assertEquals(
                LocalDateTime.of(2024, 1, 2, 10, 0),
                user.getUpdated_at()
        );
    }

    @Test
    void testGetAuthorities() {
        User user = createUser();

        Collection<? extends GrantedAuthority> authorities =
                user.getAuthorities();

        assertNotNull(authorities);
        assertTrue(authorities.isEmpty());
    }

    @Test
    void testIsAccountNonExpired() {
        User user = createUser();

        assertTrue(user.isAccountNonExpired());
    }

    @Test
    void testIsAccountNonLocked() {
        User user = createUser();

        assertTrue(user.isAccountNonLocked());
    }

    @Test
    void testIsCredentialsNonExpired() {
        User user = createUser();

        assertTrue(user.isCredentialsNonExpired());
    }

    @Test
    void testIsEnabled() {
        User user = createUser();

        assertTrue(user.isEnabled());
    }

    @Test
    void testToString() {
        User user = createUser();

        String result = user.toString();

        assertNotNull(result);
        assertTrue(result.contains("Jean"));
        assertTrue(result.contains("Dupont"));
        assertTrue(result.contains("jean.dupont"));
    }

    @Test
    void testEqualsSameObject() {
        User user = createUser();

        assertEquals(user, user);
    }

    @Test
    void testEqualsNull() {
        User user = createUser();

        assertNotEquals(user, null);
    }

    @Test
    void testEqualsDifferentClass() {
        User user = createUser();

        assertNotEquals(user, "Jean");
    }

    @Test
    void testEqualsSameValues() {
        User user1 = createUser();
        User user2 = createUser();

        assertEquals(user1, user2);
    }

    @Test
    void testEqualsDifferentId() {
        User user1 = createUser();
        User user2 = createUser();

        user2.setId(2L);

        assertNotEquals(user1, user2);
    }

    @Test
    void testEqualsDifferentFirstName() {
        User user1 = createUser();
        User user2 = createUser();

        user2.setFirstName("Pierre");

        assertNotEquals(user1, user2);
    }

    @Test
    void testEqualsDifferentLastName() {
        User user1 = createUser();
        User user2 = createUser();

        user2.setLastName("Martin");

        assertNotEquals(user1, user2);
    }

    @Test
    void testEqualsDifferentLogin() {
        User user1 = createUser();
        User user2 = createUser();

        user2.setLogin("pierre.martin");

        assertNotEquals(user1, user2);
    }

    @Test
    void testEqualsDifferentPassword() {
        User user1 = createUser();
        User user2 = createUser();

        user2.setPassword("differentPassword");

        assertNotEquals(user1, user2);
    }

    @Test
    void testEqualsDifferentCreatedAt() {
        User user1 = createUser();
        User user2 = createUser();

        user2.setCreated_at(
                LocalDateTime.of(2025, 1, 1, 10, 0)
        );

        assertNotEquals(user1, user2);
    }

    @Test
    void testEqualsDifferentUpdatedAt() {
        User user1 = createUser();
        User user2 = createUser();

        user2.setUpdated_at(
                LocalDateTime.of(2025, 2, 1, 10, 0)
        );

        assertNotEquals(user1, user2);
    }

    @Test
    void testHashCodeSameValues() {
        User user1 = createUser();
        User user2 = createUser();

        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    void testHashCodeDifferentValues() {
        User user1 = createUser();
        User user2 = createUser();

        user2.setId(2L);

        assertNotEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    void testCanEqual() {
        User user1 = createUser();
        User user2 = createUser();

        assertTrue(user1.canEqual(user2));
        assertFalse(user1.canEqual("Jean"));
        assertFalse(user1.canEqual(null));
    }
}