package com.infnet.carlos.at.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infnet.carlos.at.model.Departamento;
import com.infnet.carlos.at.repository.DepartamentoRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService {
    @Autowired
    private DepartamentoRepository departamentoRepository;

    public Departamento create(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    public List<Departamento> findAll() {
        return departamentoRepository.findAll();
    }

    public Optional<Departamento> findById(Long id) {
        return departamentoRepository.findById(id);
    }

    public void delete(Long id) {
        if(!departamentoRepository.existsById(id)) {
            throw new EntityNotFoundException("Departamento não encontrado " + id);
        }
        departamentoRepository.deleteById(id);
    }

    public Departamento update(Long id, Departamento departamentoAlterado) {
        return departamentoRepository.findById(id).map(departamento -> {
            departamento.setNome(departamentoAlterado.getNome());
            departamento.setLocal(departamentoAlterado.getLocal());
            return departamentoRepository.save(departamento);
        }).orElseGet(() -> {
            departamentoAlterado.setId(id);
            return departamentoRepository.save(departamentoAlterado);
        });
    }
}