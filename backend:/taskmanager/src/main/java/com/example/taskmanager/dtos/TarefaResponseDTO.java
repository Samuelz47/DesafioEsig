package com.example.taskmanager.dtos;

import com.example.taskmanager.model.Prioridade;
import com.example.taskmanager.model.Situacao;
import com.example.taskmanager.model.Tarefa;

import java.time.LocalDate;

public record TarefaResponseDTO(
        Long id,
        String titulo,
        String descricao,
        String responsavel,
        Prioridade prioridade,
        LocalDate deadline,
        Situacao situacao
) {
    public static TarefaResponseDTO fromEntity(Tarefa t) {
        return new TarefaResponseDTO(
                t.getId(),
                t.getTitulo(),
                t.getDescricao(),
                t.getResponsavel(),
                t.getPrioridade(),
                t.getDeadline(),
                t.getSituacao()
        );
    }
}
