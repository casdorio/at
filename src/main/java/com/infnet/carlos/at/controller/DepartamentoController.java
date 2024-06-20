package com.infnet.carlos.at.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.infnet.carlos.at.model.Departamento;
import com.infnet.carlos.at.service.DepartamentoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/public")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @PostMapping("/departamentos")
    public Departamento create(@RequestBody Departamento departamento) {
        return departamentoService.create(departamento);
    }

    @GetMapping("/departamentos")
    public List<Departamento> findAll() {
        return departamentoService.findAll();
    }

    @GetMapping("/departamentos/{id}")
    public Optional<Departamento> findById(@PathVariable Long id) {
        return departamentoService.findById(id);
    }

    @DeleteMapping("/departamentos/{id}")
    public void delete(@PathVariable Long id) {
        departamentoService.delete(id);
    }

    @PutMapping("/departamentos/{id}")
    public Departamento update(@PathVariable Long id, @RequestBody Departamento departamento) {
        return departamentoService.update(id, departamento);
    }
}
