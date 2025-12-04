package com.example.taskmanager.service;

import com.example.taskmanager.model.Tarefa;
import com.example.taskmanager.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository tarefaRepository;

    @Transactional(readOnly = true)
    public List<Tarefa> listarTodas() {
        return tarefaRepository.findAll();
    }

    @Transactional
    public Tarefa salvar(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    @Transactional(readOnly = true)
    public Optional<Tarefa> buscarPorId(Long id) {
        return tarefaRepository.findById(id);
    }

    @Transactional
    public void deletar(Long id) {
        tarefaRepository.deleteById(id);
    }

    public boolean existe(Long id) {
        return tarefaRepository.existsById(id);
    }
}

