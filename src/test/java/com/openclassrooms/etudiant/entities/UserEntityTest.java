package com.openclassrooms.etudiant.entities;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User createUser() {
        // Création d'un utilisateur pour les tests
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
        // Test de la construction d'un utilisateur avec le constructeur à tous les arguments
        LocalDateTime createdAt = LocalDateTime.of(2024, 1, 1, 10, 0);
        LocalDateTime updatedAt = LocalDateTime.of(2024, 1, 2, 10, 0);

        // Création d'un utilisateur avec le constructeur à tous les arguments
        User user = new User(
                1L,
                "Jean",
                "Dupont",
                "jean.dupont",
                "password123",
                createdAt,
                updatedAt
        );

        // Vérification que les valeurs des champs de l'utilisateur sont correctes
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
        // Test de la méthode setId() de la classe User
        User user = new User();

        // Modification de l'ID de l'utilisateur avec la méthode setId()
        user.setId(10L);

        // Vérification que l'ID de l'utilisateur est correct après l'appel de la méthode setId()
        assertEquals(10L, user.getId());
    }

    @Test
    void testSetCreatedAt() {
        // Test de la méthode setCreated_at() de la classe User
        User user = new User();
        LocalDateTime date = LocalDateTime.of(2024, 1, 1, 10, 0);

        // Modification de la date de création de l'utilisateur avec la méthode setCreated_at()
        user.setCreated_at(date);

        // Vérification que la date de création de l'utilisateur est correcte après l'appel de la méthode setCreated_at()
        assertEquals(date, user.getCreated_at());
    }

    @Test
    void testSetUpdatedAt() {
        // Test de la méthode setUpdated_at() de la classe User
        User user = new User();
        LocalDateTime date = LocalDateTime.of(2024, 1, 2, 10, 0);

        //  Modification de la date de mise à jour de l'utilisateur avec la méthode setUpdated_at()
        user.setUpdated_at(date);

        // Vérification que la date de mise à jour de l'utilisateur est correcte après l'appel de la méthode setUpdated_at()
        assertEquals(date, user.getUpdated_at());
    }

    @Test
    void testGetUsername() {
        // Test de la méthode getUsername() de la classe User
        User user = createUser();

        // Vérification que le nom d'utilisateur de l'utilisateur est correct après l'appel de la méthode getUsername()
        assertEquals("jean.dupont", user.getUsername());
    }

    @Test
    void testGetId() {
        // Test de la méthode getId() de la classe User
        User user = createUser();

        // Vérification que l'ID de l'utilisateur est correct après l'appel de la méthode getId()
        assertEquals(1L, user.getId());
    }

    @Test
    void testGetFirstName() {
        // Test de la méthode getFirstName() de la classe User
        User user = createUser();

        // Vérification que le prénom de l'utilisateur est correct après l'appel de la méthode getFirstName()
        assertEquals("Jean", user.getFirstName());
    }

    @Test
    void testGetLastName() {
        // Test de la méthode getLastName() de la classe User
        User user = createUser();

        // Vérification que le nom de famille de l'utilisateur est correct après l'appel de la méthode getLastName()
        assertEquals("Dupont", user.getLastName());
    }

    @Test
    void testGetCreatedAt() {
        // Test de la méthode getCreated_at() de la classe User
        User user = createUser();

        // Vérification que la date de création de l'utilisateur est correcte après l'appel de la méthode getCreated_at()
        assertEquals(
                LocalDateTime.of(2024, 1, 1, 10, 0),
                user.getCreated_at()
        );
    }

    @Test
    void testGetUpdatedAt() {
        // Test de la méthode getUpdated_at() de la classe User
        User user = createUser();

        // Vérification que la date de mise à jour de l'utilisateur est correcte après l'appel de la méthode getUpdated_at()
        assertEquals(
                LocalDateTime.of(2024, 1, 2, 10, 0),
                user.getUpdated_at()
        );
    }

    @Test
    void testGetAuthorities() {
        // Test de la méthode getAuthorities() de la classe User
        User user = createUser();

        // Vérification que la collection des autorités de l'utilisateur est correcte après l'appel de la méthode getAuthorities()
        Collection<? extends GrantedAuthority> authorities =
                user.getAuthorities();

        // Vérification que la collection des autorités n'est pas nulle et qu'elle est vide
        assertNotNull(authorities);
        assertTrue(authorities.isEmpty());
    }

    @Test
    void testIsAccountNonExpired() {
        // Test de la méthode isAccountNonExpired() de la classe User
        User user = createUser();

        // Vérification que le compte de l'utilisateur n'est pas expiré après l'appel de la méthode isAccountNonExpired()
        assertTrue(user.isAccountNonExpired());
    }

    @Test
    void testIsAccountNonLocked() {
        // Test de la méthode isAccountNonLocked() de la classe User
        User user = createUser();

        // Vérification que le compte de l'utilisateur n'est pas verrouillé après l'appel de la méthode isAccountNonLocked()
        assertTrue(user.isAccountNonLocked());
    }

    @Test
    void testIsCredentialsNonExpired() {
        // Test de la méthode isCredentialsNonExpired() de la classe User
        User user = createUser();

        // Vérification que les informations d'identification de l'utilisateur ne sont pas expirées après l'appel de la méthode isCredentialsNonExpired()
        assertTrue(user.isCredentialsNonExpired());
    }

    @Test
    void testIsEnabled() {
        // Test de la méthode isEnabled() de la classe User
        User user = createUser();

        // Vérification que le compte de l'utilisateur est activé après l'appel de la méthode isEnabled()
        assertTrue(user.isEnabled());
    }

    @Test
    void testToString() {
        // Test de la méthode toString() de la classe User
        User user = createUser();

        // Appel de la méthode toString() pour obtenir la représentation sous forme de chaîne de caractères de l'objet User
        String result = user.toString();

        // Vérification que la représentation sous forme de chaîne de caractères de l'objet User n'est pas nulle et contient les informations attendues
        assertNotNull(result);
        assertTrue(result.contains("Jean"));
        assertTrue(result.contains("Dupont"));
        assertTrue(result.contains("jean.dupont"));
    }

    @Test
    void testEqualsSameObject() {
        // Test de la méthode equals() de la classe User avec le même objet
        User user = createUser();

        // Vérification que l'objet est égal à lui-même
        assertEquals(user, user);
    }

    @Test
    void testEqualsNull() {
        // Test de la méthode equals() de la classe User avec un objet null
        User user = createUser();

        // Vérification que l'objet n'est pas égal à null
        assertNotEquals(user, null);
    }

    @Test
    void testEqualsDifferentClass() {
        // Test de la méthode equals() de la classe User avec un objet d'une classe différente
        User user = createUser();

        // Vérification que l'objet n'est pas égal à un objet d'une classe différente
        assertNotEquals(user, "Jean");
    }

    @Test
    void testEqualsSameValues() {
        // Test de la méthode equals() de la classe User avec des objets ayant les mêmes valeurs
        User user1 = createUser();
        User user2 = createUser();

        // Vérification que les deux objets sont égaux car ils ont les mêmes valeurs
        assertEquals(user1, user2);
    }

    @Test
    void testEqualsDifferentId() {
        // Test de la méthode equals() de la classe User avec des objets ayant des IDs différents
        User user1 = createUser();
        User user2 = createUser();

        // Modification de l'ID du deuxième utilisateur pour qu'il soit différent du premier
        user2.setId(2L);

        // Vérification que les deux objets ne sont pas égaux car ils ont des IDs différents
        assertNotEquals(user1, user2);
    }

    @Test
    void testEqualsDifferentFirstName() {
        // Test de la méthode equals() de la classe User avec des objets ayant des prénoms différents
        User user1 = createUser();
        User user2 = createUser();

        // Modification du prénom du deuxième utilisateur pour qu'il soit différent du premier
        user2.setFirstName("Pierre");

        // Vérification que les deux objets ne sont pas égaux car ils ont des prénoms différents
        assertNotEquals(user1, user2);
    }

    @Test
    void testEqualsDifferentLastName() {
        // Test de la méthode equals() de la classe User avec des objets ayant des noms de famille différents
        User user1 = createUser();
        User user2 = createUser();

        // Modification du nom de famille du deuxième utilisateur pour qu'il soit différent du premier
        user2.setLastName("Martin");

        // Vérification que les deux objets ne sont pas égaux car ils ont des noms de famille différents
        assertNotEquals(user1, user2);
    }

    @Test
    void testEqualsDifferentLogin() {
        //  Test de la méthode equals() de la classe User avec des objets ayant des logins différents
        User user1 = createUser();
        User user2 = createUser();

        // Modification du login du deuxième utilisateur pour qu'il soit différent du premier
        user2.setLogin("pierre.martin");

        // Vérification que les deux objets ne sont pas égaux car ils ont des logins différents
        assertNotEquals(user1, user2);
    }

    @Test
    void testEqualsDifferentPassword() {
        // Test de la méthode equals() de la classe User avec des objets ayant des mots de passe différents
        User user1 = createUser();
        User user2 = createUser();

        // Modification du mot de passe du deuxième utilisateur pour qu'il soit différent du premier
        user2.setPassword("differentPassword");

        // Vérification que les deux objets ne sont pas égaux car ils ont des mots de passe différents
        assertNotEquals(user1, user2);
    }

    @Test
    void testEqualsDifferentCreatedAt() {
        // Test de la méthode equals() de la classe User avec des objets ayant des dates de création différentes
        User user1 = createUser();
        User user2 = createUser();

        // Modification de la date de création du deuxième utilisateur pour qu'elle soit différente du premier
        user2.setCreated_at(
                LocalDateTime.of(2025, 1, 1, 10, 0)
        );

        // Vérification que les deux objets ne sont pas égaux car ils ont des dates de création différentes
        assertNotEquals(user1, user2);
    }

    @Test
    void testEqualsDifferentUpdatedAt() {
        // Test de la méthode equals() de la classe User avec des objets ayant des dates de mise à jour différentes
        User user1 = createUser();
        User user2 = createUser();

        // Modification de la date de mise à jour du deuxième utilisateur pour qu'elle soit différente du premier
        user2.setUpdated_at(
                LocalDateTime.of(2025, 2, 1, 10, 0)
        );

        // Vérification que les deux objets ne sont pas égaux car ils ont des dates de mise à jour différentes
        assertNotEquals(user1, user2);
    }

    @Test
    void testHashCodeSameValues() {
        // Test de la méthode hashCode() de la classe User avec des objets ayant les mêmes valeurs
        User user1 = createUser();
        User user2 = createUser();

        // Vérification que les deux objets ont le même code de hachage car ils ont les mêmes valeurs
        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    void testHashCodeDifferentValues() {
        // Test de la méthode hashCode() de la classe User avec des objets ayant des valeurs différentes
        User user1 = createUser();
        User user2 = createUser();

        // Modification de l'ID du deuxième utilisateur pour qu'il soit différent du premier
        user2.setId(2L);

        // Vérification que les deux objets ont des codes de hachage différents car ils ont des valeurs différentes
        assertNotEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    void testCanEqual() {
        // Test de la méthode canEqual() de la classe User
        User user1 = createUser();
        User user2 = createUser();

        // Vérification que l'objet user1 peut être égal à l'objet user2
        assertTrue(user1.canEqual(user2));
        assertFalse(user1.canEqual("Jean"));
        assertFalse(user1.canEqual(null));
    }
}