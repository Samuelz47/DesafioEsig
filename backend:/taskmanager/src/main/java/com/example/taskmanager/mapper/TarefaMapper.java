package com.example.taskmanager.mapper;

import com.example.taskmanager.dtos.TarefaRequestDTO;
import com.example.taskmanager.dtos.TarefaResponseDTO;
import com.example.taskmanager.model.Tarefa;
import org.springframework.stereotype.Component;

@Component
public class TarefaMapper {
    public Tarefa toEntity(TarefaRequestDTO dto) {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(dto.titulo());
        tarefa.setDescricao(dto.descricao());
        tarefa.setResponsavel(dto.responsavel());
        tarefa.setPrioridade(dto.prioridade());
        tarefa.setDeadline(dto.deadline());
        tarefa.setSituacao(dto.situacao());
        return tarefa;
    }

    public TarefaResponseDTO toDTO(Tarefa entity) {
        return new TarefaResponseDTO(
                entity.getId(),
                entity.getTitulo(),
                entity.getDescricao(),
                entity.getResponsavel(),
                entity.getPrioridade(),
                entity.getDeadline(),
                entity.getSituacao()
        );
    }

    public void updateEntityFromDTO(TarefaRequestDTO dto, Tarefa entity) {
        entity.setTitulo(dto.titulo());
        entity.setDescricao(dto.descricao());
        entity.setResponsavel(dto.responsavel());
        entity.setPrioridade(dto.prioridade());
        entity.setDeadline(dto.deadline());
        entity.setSituacao(dto.situacao());
    }
}
