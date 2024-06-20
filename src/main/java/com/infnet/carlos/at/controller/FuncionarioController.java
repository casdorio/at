package com.infnet.carlos.at.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.infnet.carlos.at.model.Funcionario;
import com.infnet.carlos.at.service.FuncionarioService;

import java.util.List;

@RestController
@RequestMapping("/api/public")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping("/funcionarios/{id}")
    public Funcionario create(@PathVariable Long id, @RequestBody Funcionario funcionario) {
        return funcionarioService.create(id, funcionario);
    }

    @GetMapping("/funcionarios")
    public List<Funcionario> findAll() {
        return funcionarioService.findAll();
    }

    @GetMapping("/funcionarios/{id}")
    public Funcionario findById(@PathVariable Long id) {
        return funcionarioService.findById(id);
    }

    @DeleteMapping("/funcionarios/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return funcionarioService.delete(id);
    }

    @PutMapping("/funcionarios/{id}")
    public Funcionario update(@PathVariable Long id, @RequestBody Funcionario funcionario) {
        return funcionarioService.update(id, funcionario);
    }

    @GetMapping("/funcionarios/departamento/{id}")
    public ResponseEntity<?> findByDepartamentoId(@PathVariable Long id) {
        return funcionarioService.findByDepartamentoId(id);
    }
}
