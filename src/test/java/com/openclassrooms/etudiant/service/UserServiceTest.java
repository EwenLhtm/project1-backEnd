package com.openclassrooms.etudiant.service;

import com.openclassrooms.etudiant.entities.User;
import com.openclassrooms.etudiant.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";
    private static final String LOGIN = "LOGIN";
    private static final String PASSWORD = "PASSWORD";
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtService jwtService;
    @InjectMocks
    private UserService userService;

    @Test
    public void test_create_user() {
        // Crée un nouvel utilisateur avec des informations valides
        User user = new User();
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setLogin(LOGIN);
        user.setPassword(PASSWORD);
        when(passwordEncoder.encode(PASSWORD)).thenReturn(PASSWORD);
        when(userRepository.findByLogin(any())).thenReturn(Optional.empty());

        // Enregistre l'utilisateur en utilisant le service
        userService.register(user);

        // Vérifie que l'utilisateur a été enregistré correctement en utilisant un ArgumentCaptor
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());
        assertThat(userCaptor.getValue()).isEqualTo(user);
    }

    @Test
    public void test_create_already_exist_user_throws_IllegalArgumentException() {
        // Crée un utilisateur avec un login déjà existant
        User user = new User();
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setLogin(LOGIN);
        user.setPassword(PASSWORD);
        when(passwordEncoder.encode(PASSWORD)).thenReturn(PASSWORD);
        when(userRepository.findByLogin(any())).thenReturn(Optional.of(user));

        // Vérifie que la création d'un utilisateur avec un login déjà existant lance une exception IllegalArgumentException
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> userService.register(user));
    }


    @Test
    public void test_create_null_user_throws_IllegalArgumentException() {
        // Vérifie que la création d'un utilisateur null lance une exception IllegalArgumentException
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> userService.register(null));
    }

    @Test
    public void test_login_user() {
        // Crée un utilisateur avec des informations valides
        User user = new User();
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setLogin(LOGIN);
        user.setPassword(PASSWORD);
        when(passwordEncoder.matches(PASSWORD, PASSWORD)).thenReturn(true);
        when(userRepository.findByLogin(any())).thenReturn(Optional.of(user));
        when(jwtService.generateToken(any())).thenReturn("jwtToken");

        // Vérifie que la connexion de l'utilisateur avec des informations valides retourne un token JWT
        String jwtToken = userService.login(LOGIN, PASSWORD);

        // Vérifie que le token JWT n'est pas null
        assertThat(jwtToken).isNotNull();
    }

    @Test 
    public void test_login_user_with_invalid_credentials_throws_IllegalArgumentException() {
        // Crée un utilisateur avec des informations valides
        User user = new User();
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setLogin(LOGIN);
        user.setPassword(PASSWORD);
        when(passwordEncoder.matches(PASSWORD, PASSWORD)).thenReturn(true);
        when(userRepository.findByLogin(any())).thenReturn(Optional.of(user));
        when(jwtService.generateToken(any())).thenReturn("jwtToken");

        // Vérifie que la connexion de l'utilisateur avec des informations invalides lance une exception IllegalArgumentException
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> userService.login(LOGIN, "wrongPassword"));
    }

    @Test 
    public void test_login_user_dont_exist_throws_IllegalArgumentException() {
        // Simule qu'aucun utilisateur n'existe avec le login donné
        when(userRepository.findByLogin(any())).thenReturn(Optional.empty());

        // Vérifie que la connexion d'un utilisateur qui n'existe pas lance une exception IllegalArgumentException
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> userService.login(LOGIN, PASSWORD));
    }

    @Test
    public void test_login_user_with_null_login_throws_IllegalArgumentException() {
        // Vérifie que la connexion d'un utilisateur avec un login null lance une exception IllegalArgumentException
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> userService.login(null, PASSWORD));
    }

    @Test
    public void test_login_user_with_null_password_throws_IllegalArgumentException() {
        // Vérifie que la connexion d'un utilisateur avec un mot de passe null lance une exception IllegalArgumentException
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> userService.login(LOGIN, null));
    }
}
