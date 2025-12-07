package com.example.taskmanager.controller;

import com.example.taskmanager.dtos.TarefaRequestDTO;
import com.example.taskmanager.dtos.TarefaResponseDTO;
import com.example.taskmanager.mapper.TarefaMapper;
import com.example.taskmanager.model.Situacao;
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
    public ResponseEntity<List<Tarefa>> listar(@RequestParam(required = false) Long id, @RequestParam(required = false) String titulo, @RequestParam(required = false) String responsavel, @RequestParam(required = false) Situacao situacao) {
        List<Tarefa> tarefas = tarefaService.listar(id, titulo, responsavel, situacao);

        return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarPorId(@PathVariable Long id) {
        return tarefaService.buscarPorId(id)
                .map(tarefa -> ResponseEntity.ok(tarefa))
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
