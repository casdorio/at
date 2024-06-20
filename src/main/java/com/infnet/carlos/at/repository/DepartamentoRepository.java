package com.infnet.carlos.at.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infnet.carlos.at.model.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
}
