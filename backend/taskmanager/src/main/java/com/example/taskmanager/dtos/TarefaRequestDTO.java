package com.example.taskmanager.dtos;

import com.example.taskmanager.model.Prioridade;
import com.example.taskmanager.model.Situacao;

import java.time.LocalDate;

public record TarefaRequestDTO(
    String titulo,
    String descricao,
    String responsavel,
    Prioridade prioridade,
    LocalDate deadline,
    Situacao situacao) { }
