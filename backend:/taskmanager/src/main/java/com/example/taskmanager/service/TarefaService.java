package com.example.taskmanager.service;

import com.example.taskmanager.model.Situacao;
import com.example.taskmanager.model.Tarefa;
import com.example.taskmanager.repository.TarefaRepository;
import com.example.taskmanager.repository.TarefaSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository tarefaRepository;

    @Transactional(readOnly = true)
    public List<Tarefa> listar(Long id, String titulo, String responsavel, Situacao situacao) {
        Specification<Tarefa> spec = TarefaSpecification.comFiltros(id, titulo, responsavel, situacao);
        return tarefaRepository.findAll(spec);
    }

    @Transactional
    public Tarefa salvar(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public Optional<Tarefa> buscarPorId(Long id) { return tarefaRepository.findById(id); }

    @Transactional
    public void deletar(Long id) {
        tarefaRepository.deleteById(id);
    }

    public boolean existe(Long id) {
        return tarefaRepository.existsById(id);
    }
}

