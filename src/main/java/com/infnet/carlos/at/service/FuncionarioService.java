package com.infnet.carlos.at.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.infnet.carlos.at.model.Funcionario;
import com.infnet.carlos.at.repository.DepartamentoRepository;
import com.infnet.carlos.at.repository.FuncionarioRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class FuncionarioService {
    
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    
    @Autowired
    private DepartamentoRepository departamentoRepository;

    public Funcionario create(Long id, Funcionario funcionario) {
        return departamentoRepository.findById(id).map(departamento -> {
            funcionario.setDepartamento(departamento);
            return funcionarioRepository.save(funcionario);
        }).orElseThrow(() -> new RuntimeException("Departamento não encontrado " +id));
    }

    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }

    public Funcionario findById(Long id) {
        return funcionarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Funcionario não encontrado "+ id));
    }

    public ResponseEntity<?> delete(Long id) {
        return funcionarioRepository.findById(id).map(funcionario -> {
            funcionarioRepository.delete(funcionario);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new RuntimeException("Funcionário não encontrado " +id));
    }

    public Funcionario update(Long id, Funcionario funcionarioAlterado) {
        return funcionarioRepository.findById(id).map(funcionario ->{
            funcionario.setNome(funcionarioAlterado.getNome());
            funcionario.setEndereco(funcionarioAlterado.getEndereco());
            funcionario.setTelefone(funcionarioAlterado.getTelefone());
            funcionario.setEmail(funcionarioAlterado.getEmail());
            funcionario.setDataNascimento(funcionarioAlterado.getDataNascimento());
            return funcionarioRepository.save(funcionario);
        }).orElseThrow(() -> new RuntimeException("Funcionário não encontrado " + id));
    }

    public ResponseEntity<?> findByDepartamentoId(Long id) {
        List<Funcionario> funcionarios = funcionarioRepository.findByDepartamentoId(id);
        return ResponseEntity.ok(funcionarios);
    }
}
