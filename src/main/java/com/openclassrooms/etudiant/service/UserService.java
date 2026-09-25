package com.openclassrooms.etudiant.service;

import com.openclassrooms.etudiant.entities.User;
import com.openclassrooms.etudiant.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public void register(User user) {
        // Verification des champs obligatoires
        Assert.notNull(user, "User must not be null");

        // Vérification si l'utilisateur existe déjà
        Optional<User> optionalUser = userRepository.findByLogin(user.getLogin());
        if (optionalUser.isPresent()) {
            // L'utilisateur existe déjà, lancer une exception
            throw new IllegalArgumentException("User with login " + user.getLogin() + " already exists");
        }
        // Encodage du mot de passe avant de sauvegarder l'utilisateur
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Sauvegarde de l'utilisateur dans la base de données
        userRepository.save(user);
    }

    public String login(String login, String password) {
        // Vérification des champs obligatoires
        Assert.notNull(login, "Login must not be null");
        Assert.notNull(password, "Password must not be null");

        // Recherche de l'utilisateur par login
        Optional<User> user = userRepository.findByLogin(login);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            // On génère un token JWT pour l'utilisateur authentifié
            UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                    .username(login).password(password).build();

            String jwtToken = jwtService.generateToken(userDetails);


            if (jwtToken == null) {
                // Si la génération du token échoue, lancer une exception
                throw new IllegalStateException("JWT token generation failed");
            }

            // Retourner le token JWT généré
            return jwtToken;
        } else {
            // Si l'utilisateur n'existe pas ou si le mot de passe est incorrect, lancer une exception
            throw new IllegalArgumentException("Invalid credentials");
        }
    }


}
