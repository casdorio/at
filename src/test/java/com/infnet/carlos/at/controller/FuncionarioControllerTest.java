package com.infnet.carlos.at.controller;

import com.infnet.carlos.at.model.Funcionario;
import com.infnet.carlos.at.service.FuncionarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class FuncionarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FuncionarioService funcionarioService;

    @Test
    @WithMockUser
    void testFindAll() throws Exception {
        when(funcionarioService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/public/funcionarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @WithMockUser
    void testFindById() throws Exception {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(1L);
        funcionario.setNome("Carlos");
        funcionario.setEndereco("Rua A");
        funcionario.setTelefone("123456789");
        funcionario.setEmail("carlos@example.com");
        funcionario.setDataNascimento(LocalDate.of(1990, 1, 1));

        when(funcionarioService.findById(anyLong())).thenReturn(funcionario);

        mockMvc.perform(get("/api/public/funcionarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Carlos"));
    }

    @Test
    @WithMockUser
    void testCreate() throws Exception {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(1L);
        funcionario.setNome("Carlos");
        funcionario.setEndereco("Rua A");
        funcionario.setTelefone("123456789");
        funcionario.setEmail("carlos@example.com");
        funcionario.setDataNascimento(LocalDate.of(1990, 1, 1));

        when(funcionarioService.create(anyLong(), any(Funcionario.class))).thenReturn(funcionario);

        mockMvc.perform(post("/api/public/funcionarios/departamento/1")
                .contentType("application/json")
                .content("{\"nome\":\"Carlos\",\"endereco\":\"Rua A\",\"telefone\":\"123456789\",\"email\":\"carlos@example.com\",\"dataNascimento\":\"1990-01-01\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Carlos"));
    }
}
