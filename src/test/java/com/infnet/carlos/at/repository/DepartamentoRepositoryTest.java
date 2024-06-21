package com.infnet.carlos.at.repository;

import com.infnet.carlos.at.model.Departamento;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class DepartamentoRepositoryTest {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Test
    void testSaveAndFindById() {
        Departamento departamento = new Departamento();
        departamento.setNome("RH");
        departamento.setLocal("São Paulo");

        departamento = departamentoRepository.save(departamento);

        Departamento foundDepartamento = departamentoRepository.findById(departamento.getId()).orElse(null);
        assertNotNull(foundDepartamento);
    }
}
