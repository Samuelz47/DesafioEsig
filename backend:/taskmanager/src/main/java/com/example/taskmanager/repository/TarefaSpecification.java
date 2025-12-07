package com.example.taskmanager.repository;
import com.example.taskmanager.model.Situacao;
import com.example.taskmanager.model.Tarefa;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class TarefaSpecification {

    public static Specification<Tarefa> comFiltros(Long id, String titulo, String responsavel, Situacao situacao) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (id != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), id));
            }

            if (titulo != null && !titulo.isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("titulo")),
                        "%" + titulo.toLowerCase() + "%"
                ));
            }

            if (responsavel != null && !responsavel.isEmpty()) { // <--- Adicionar isso
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("responsavel")), // Certifique-se que existe esse campo no Model Tarefa
                        "%" + responsavel.toLowerCase() + "%"
                ));
            }

            if (situacao != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), situacao));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
