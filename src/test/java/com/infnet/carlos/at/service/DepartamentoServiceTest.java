package com.infnet.carlos.at.service;

import com.infnet.carlos.at.model.Departamento;
import com.infnet.carlos.at.repository.DepartamentoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartamentoServiceTest {

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Test
    void testCreate() {
        Departamento departamento = new Departamento();
        departamento.setNome("Financeiro");
        departamento.setLocal("Rio de Janeiro");

        Departamento savedDepartamento = departamentoService.create(departamento);

        assertNotNull(savedDepartamento);
        assertEquals("Financeiro", savedDepartamento.getNome());
    }

    @Test
    void testFindById() {
        Departamento departamento = new Departamento();
        departamento.setNome("Financeiro");
        departamento.setLocal("Rio de Janeiro");

        Departamento savedDepartamento = departamentoRepository.save(departamento);

        Optional<Departamento> foundDepartamento = departamentoService.findById(savedDepartamento.getId());
        assertTrue(foundDepartamento.isPresent());
        assertEquals("Financeiro", foundDepartamento.get().getNome());
    }
}
