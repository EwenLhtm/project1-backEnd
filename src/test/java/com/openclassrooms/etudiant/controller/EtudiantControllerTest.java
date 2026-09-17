package com.openclassrooms.etudiant.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.etudiant.dto.EtudiantRequestDTO;
import com.openclassrooms.etudiant.entities.Etudiant;
import com.openclassrooms.etudiant.repository.EtudiantRepository;
import com.openclassrooms.etudiant.service.EtudiantService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
public class EtudiantControllerTest {
    
    private static final String URL = "/api/etudiant";
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";
    private static final String EMAIL = "john.doe@example.com";

    @Container 
    static MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest");

    @Autowired
    private EtudiantService etudiantService;
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @DynamicPropertySource
    static void configureTestProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", () -> mySQLContainer.getJdbcUrl());
        registry.add("spring.datasource.username", () -> mySQLContainer.getUsername());
        registry.add("spring.datasource.password", () -> mySQLContainer.getPassword());
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create");
    }

    @AfterEach
    public void afterEach() {
        etudiantRepository.deleteAll();
    }

    @Test
    public void createEtudiantWithoutRequiredData() throws Exception {
        // GIVEN
        EtudiantRequestDTO etudiantRequestDTO = new EtudiantRequestDTO();

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                        .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_USER")))
                        .content(objectMapper.writeValueAsString(etudiantRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void createEtudiantAlreadyExistEmail() throws Exception {
        // GIVEN
        Etudiant etudiant = new Etudiant();
        etudiant.setFirstName(FIRST_NAME);
        etudiant.setLastName(LAST_NAME);
        etudiant.setEmail(EMAIL);
        etudiantService.create(etudiant);

        EtudiantRequestDTO etudiantRequestDTO = new EtudiantRequestDTO();
        etudiantRequestDTO.setFirstName(FIRST_NAME);
        etudiantRequestDTO.setLastName(LAST_NAME);
        etudiantRequestDTO.setEmail(EMAIL);

        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                        .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_USER")))
                        .content(objectMapper.writeValueAsString(etudiantRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }   

    @Test
    public void createEtudiant() throws Exception {
        // GIVEN
        EtudiantRequestDTO etudiantRequestDTO = new EtudiantRequestDTO();
        etudiantRequestDTO.setFirstName(FIRST_NAME);
        etudiantRequestDTO.setLastName(LAST_NAME);
        etudiantRequestDTO.setEmail(EMAIL);

        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                        .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_USER")))
                        .content(objectMapper.writeValueAsString(etudiantRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test 
    public void getAllEtudiants() throws Exception {
        // GIVEN
        Etudiant etudiant1 = new Etudiant();
        etudiant1.setFirstName(FIRST_NAME);
        etudiant1.setLastName(LAST_NAME);
        etudiant1.setEmail(EMAIL);
        etudiantService.create(etudiant1);

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setFirstName("Jane");
        etudiant2.setLastName("Smith");
        etudiant2.setEmail("jane.smith@example.com");
        etudiantService.create(etudiant2);

        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.get(URL)
                        .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_USER")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test 
    public void getEtudiantInexistantId() throws Exception {
        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.get(URL + "/{id}", 999L)
                        .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_USER")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test 
    public void getEtudiantById() throws Exception {
        // GIVEN
        Etudiant etudiant = new Etudiant();
        etudiant.setFirstName(FIRST_NAME);
        etudiant.setLastName(LAST_NAME);
        etudiant.setEmail(EMAIL);
        etudiantService.create(etudiant);

        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.get(URL + "/{id}", etudiant.getId())
                        .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_USER")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(FIRST_NAME))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value(LAST_NAME))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(EMAIL));
    }

    @Test 
    public void updateEtudiantInexistantId() throws Exception {
        // GIVEN
        EtudiantRequestDTO etudiantRequestDTO = new EtudiantRequestDTO();
        etudiantRequestDTO.setFirstName(FIRST_NAME);
        etudiantRequestDTO.setLastName(LAST_NAME);
        etudiantRequestDTO.setEmail(EMAIL);

        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.put(URL + "/{id}", 999L)
                        .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_USER")))
                        .content(objectMapper.writeValueAsString(etudiantRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void updateEtudiant() throws Exception {
        // GIVEN
        Etudiant etudiant = new Etudiant();
        etudiant.setFirstName(FIRST_NAME);
        etudiant.setLastName(LAST_NAME);
        etudiant.setEmail(EMAIL);
        
        Etudiant createdEtudiant = etudiantRepository.saveAndFlush(etudiant);

        EtudiantRequestDTO etudiantRequestDTO = new EtudiantRequestDTO();
        etudiantRequestDTO.setFirstName("UpdatedFirstName");
        etudiantRequestDTO.setLastName("UpdatedLastName");
        etudiantRequestDTO.setEmail(EMAIL);

        // WHEN
       mockMvc.perform(MockMvcRequestBuilders.put(URL + "/{id}", createdEtudiant.getId())
                        .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_USER")))
                        .content(objectMapper.writeValueAsString(etudiantRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }   

    @Test 
    public void deleteEtudiantInexistantId() throws Exception {
        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.delete(URL + "/{id}", 999L)
                        .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_USER")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void deleteEtudiant() throws Exception {
        // GIVEN
        Etudiant etudiant = new Etudiant();
        etudiant.setFirstName(FIRST_NAME);
        etudiant.setLastName(LAST_NAME);
        etudiant.setEmail(EMAIL);
        etudiantService.create(etudiant);

        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.delete(URL + "/{id}", etudiant.getId())
                        .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_USER")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
