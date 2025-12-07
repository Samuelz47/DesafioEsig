import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { TaskService } from '../../services/task';
import { Task } from '../../models/task.model';

@Component({
  selector: 'app-task-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule], // FormsModule é essencial para o formulário funcionar
  templateUrl: './task-form.html',
  styleUrl: './task-form.scss'
})
export class TaskForm implements OnInit {
  // Inicializa uma tarefa vazia
  task: Task = {
    titulo: '',
    descricao: '',
    responsavel: '',
    prioridade: 'MEDIA', // Valor padrão
    deadline: '',
    situacao: 'EM_ANDAMENTO'
  };
  
  isEditing = false;

  constructor(
    private taskService: TaskService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    // Verifica se tem ID na URL (Modo Edição)
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEditing = true;
      this.taskService.getTaskById(+id).subscribe(task => {
        this.task = task;
      });
    }
  }

  onSubmit() {
    if (this.isEditing) {
      this.taskService.updateTask(this.task).subscribe(() => {
        this.router.navigate(['/tasks']); // Volta para a lista
      });
    } else {
      this.taskService.createTask(this.task).subscribe(() => {
        this.router.navigate(['/tasks']); // Volta para a lista
      });
    }
  }
}