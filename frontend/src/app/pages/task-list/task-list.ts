import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common'; 
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { TaskService } from '../../services/task';
import { Task } from '../../models/task.model';

@Component({
  selector: 'app-task-list',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './task-list.html',
  styleUrl: './task-list.scss'
})
export class TaskList implements OnInit {
  tasks: Task[] = [];
  isLoading = true;

  filtro = {
    id: '',
    titulo: '',
    responsavel: '',
    situacao: ''
  };

  constructor(
    private taskService: TaskService,
    private cdr: ChangeDetectorRef 
  ) {}

  ngOnInit(): void {
    this.buscarTarefas();
  }

  buscarTarefas() {
    this.isLoading = true;
    
    const filtroLimpo: any = {};
    if(this.filtro.id) filtroLimpo.id = this.filtro.id;
    if(this.filtro.titulo) filtroLimpo.titulo = this.filtro.titulo;
    if(this.filtro.responsavel) filtroLimpo.responsavel = this.filtro.responsavel;
    if(this.filtro.situacao) filtroLimpo.situacao = this.filtro.situacao;

    this.taskService.getTasks(filtroLimpo).subscribe({
      next: (data) => {
        this.tasks = data;
        this.isLoading = false;
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Erro:', err);
        this.isLoading = false;
        this.cdr.detectChanges();
      }
    });
  }

  limparFiltros() {
    this.filtro = { id: '', titulo: '', responsavel: '', situacao: '' };
    this.buscarTarefas();
  }

  deleteTask(id: number) {
    if (confirm('Deseja excluir?')) {
      this.taskService.deleteTask(id).subscribe(() => this.buscarTarefas());
    }
  }

  concludeTask(task: Task) {
    this.taskService.concludeTask(task).subscribe(() => this.buscarTarefas());
  }
}