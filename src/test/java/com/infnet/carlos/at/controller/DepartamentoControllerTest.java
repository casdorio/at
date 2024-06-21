package com.infnet.carlos.at.controller;

import com.infnet.carlos.at.model.Departamento;
import com.infnet.carlos.at.service.DepartamentoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DepartamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartamentoService departamentoService;

    @Test
    @WithMockUser
    void testFindAll() throws Exception {
        when(departamentoService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/public/departamentos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @WithMockUser
    void testFindById() throws Exception {
        Departamento departamento = new Departamento();
        departamento.setId(1L);
        departamento.setNome("Financeiro");
        departamento.setLocal("Rio de Janeiro");

        when(departamentoService.findById(anyLong())).thenReturn(Optional.of(departamento));

        mockMvc.perform(get("/api/public/departamentos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Financeiro"));
    }

    @Test
    @WithMockUser
    void testCreate() throws Exception {
        Departamento departamento = new Departamento();
        departamento.setId(1L);
        departamento.setNome("Financeiro");
        departamento.setLocal("Rio de Janeiro");

        when(departamentoService.create(any(Departamento.class))).thenReturn(departamento);

        mockMvc.perform(post("/api/public/departamentos")
                .contentType("application/json")
                .content("{\"nome\":\"Financeiro\",\"local\":\"Rio de Janeiro\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Financeiro"));
    }
}
