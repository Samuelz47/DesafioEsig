import { Component, OnInit, ChangeDetectorRef } from '@angular/core'; // <--- Importe ChangeDetectorRef
import { CommonModule } from '@angular/common'; 
import { RouterModule } from '@angular/router';
import { TaskService } from '../../services/task';
import { Task } from '../../models/task.model';

@Component({
  selector: 'app-task-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './task-list.html',
  styleUrl: './task-list.scss'
})
export class TaskList implements OnInit {
  tasks: Task[] = [];
  isLoading = true;

  // Injete o cdr no construtor
  constructor(
    private taskService: TaskService,
    private cdr: ChangeDetectorRef 
  ) {}

  ngOnInit(): void {
    this.loadTasks();
  }

  loadTasks() {
    this.isLoading = true;
    this.taskService.getTasks().subscribe({
      next: (data) => {
        this.tasks = data;
        this.isLoading = false;
        
        // A MARRETA: Obriga o Angular a atualizar a tela AGORA
        this.cdr.detectChanges(); 
      },
      error: (err) => {
        console.error('Erro:', err);
        this.isLoading = false;
        this.cdr.detectChanges(); // Aqui também
      }
    });
  }

  deleteTask(id: number) {
    if (confirm('Deseja excluir?')) {
      this.taskService.deleteTask(id).subscribe(() => this.loadTasks());
    }
  }

  concludeTask(task: Task) {
    this.taskService.concludeTask(task).subscribe(() => this.loadTasks());
  }
}