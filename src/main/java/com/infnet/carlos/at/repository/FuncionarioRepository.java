package com.infnet.carlos.at.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infnet.carlos.at.model.Funcionario;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    List<Funcionario> findByDepartamentoId(Long id);
}
