package com.example.taskmanager.controller;

import com.example.taskmanager.dtos.TarefaResponseDTO;
import com.example.taskmanager.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {
    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public ResponseEntity<List<TarefaResponseDTO>> listar () {
        List<TarefaResponseDTO> lista = tarefaService.listarTodas()
                .stream()
                .map(TarefaResponseDTO::fromEntity)
                .toList();

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> buscarPorId(@PathVariable Long id) {
        return tarefaService.buscarPorId(id)
                .map(TarefaResponseDTO::fromEntity);
    }
}
