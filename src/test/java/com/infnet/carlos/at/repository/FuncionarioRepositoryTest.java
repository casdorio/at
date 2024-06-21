package com.infnet.carlos.at.repository;

import com.infnet.carlos.at.model.Departamento;
import com.infnet.carlos.at.model.Funcionario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class FuncionarioRepositoryTest {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Test
    void testFindByDepartamentoId() {
        Departamento departamento = new Departamento();
        departamento.setNome("TI");
        departamento.setLocal("Curitiba");
        departamento = departamentoRepository.save(departamento);

        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Ana");
        funcionario.setEndereco("Rua B");
        funcionario.setTelefone("987654321");
        funcionario.setEmail("ana@example.com");
        funcionario.setDataNascimento(LocalDate.of(1985, 5, 10));
        funcionario.setDepartamento(departamento);

        funcionarioRepository.save(funcionario);

        List<Funcionario> funcionarios = funcionarioRepository.findByDepartamentoId(departamento.getId());
        assertNotNull(funcionarios);
        assertEquals(1, funcionarios.size());
        assertEquals("Ana", funcionarios.get(0).getNome());
    }
}
