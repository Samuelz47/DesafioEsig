package com.example.taskmanager.controller;

import com.example.taskmanager.dtos.TarefaRequestDTO;
import com.example.taskmanager.dtos.TarefaResponseDTO;
import com.example.taskmanager.mapper.TarefaMapper;
import com.example.taskmanager.model.Tarefa;
import com.example.taskmanager.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {
    @Autowired
    private TarefaService tarefaService;

    @Autowired
    private TarefaMapper mapper;

    @GetMapping
    public ResponseEntity<List<TarefaResponseDTO>> listar () {
        List<TarefaResponseDTO> lista = tarefaService.listarTodas()
                .stream()
                .map(mapper::toDTO)
                .toList();

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> buscarPorId(@PathVariable Long id) {
        return tarefaService.buscarPorId(id)
                .map(mapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TarefaResponseDTO> criar(@RequestBody TarefaRequestDTO dados) {
        Tarefa tarefa = mapper.toEntity(dados);
        Tarefa salva = tarefaService.salvar(tarefa);
        return ResponseEntity.ok(mapper.toDTO(salva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> atualizar(@PathVariable Long id, @RequestBody TarefaRequestDTO dados) {
        return tarefaService.buscarPorId(id)
                .map(tarefaExistente -> {
                    mapper.updateEntityFromDTO(dados, tarefaExistente);
                    Tarefa salva = tarefaService.salvar(tarefaExistente);
                    return ResponseEntity.ok(mapper.toDTO(salva));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (tarefaService.existe(id)) {
            tarefaService.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
