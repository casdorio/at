package com.infnet.carlos.at.service;

import com.infnet.carlos.at.model.Departamento;
import com.infnet.carlos.at.model.Funcionario;
import com.infnet.carlos.at.repository.DepartamentoRepository;
import com.infnet.carlos.at.repository.FuncionarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class FuncionarioServiceTest {

    @Autowired
    private FuncionarioService funcionarioService;

    @MockBean
    private FuncionarioRepository funcionarioRepository;

    @MockBean
    private DepartamentoRepository departamentoRepository;

    @Test
    void testCreate() {
        Departamento departamento = new Departamento();
        departamento.setId(1L);
        departamento.setNome("Financeiro");
        departamento.setLocal("Rio de Janeiro");

        Funcionario funcionario = new Funcionario();
        funcionario.setId(1L);
        funcionario.setNome("Carlos");
        funcionario.setEndereco("Rua A");
        funcionario.setTelefone("123456789");
        funcionario.setEmail("carlos@example.com");
        funcionario.setDataNascimento(LocalDate.of(1990, 1, 1));
        funcionario.setDepartamento(departamento);

        when(departamentoRepository.findById(anyLong())).thenReturn(Optional.of(departamento));
        when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(funcionario);

        Funcionario savedFuncionario = funcionarioService.create(1L, funcionario);

        assertNotNull(savedFuncionario);
        assertEquals("Carlos", savedFuncionario.getNome());
        assertEquals(departamento.getId(), savedFuncionario.getDepartamento().getId());
    }

    @Test
    void testFindById() {
        Departamento departamento = new Departamento();
        departamento.setId(1L);
        departamento.setNome("Financeiro");
        departamento.setLocal("Rio de Janeiro");

        Funcionario funcionario = new Funcionario();
        funcionario.setId(1L);
        funcionario.setNome("Carlos");
        funcionario.setEndereco("Rua A");
        funcionario.setTelefone("123456789");
        funcionario.setEmail("carlos@example.com");
        funcionario.setDataNascimento(LocalDate.of(1990, 1, 1));
        funcionario.setDepartamento(departamento);

        when(funcionarioRepository.findById(anyLong())).thenReturn(Optional.of(funcionario));

        Funcionario foundFuncionario = funcionarioService.findById(1L);
        assertNotNull(foundFuncionario);
        assertEquals("Carlos", foundFuncionario.getNome());
    }
}
