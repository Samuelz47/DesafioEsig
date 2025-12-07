import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Task } from '../models/task.model';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private apiUrl = 'https://gerenciadordetarefas-f8g8.onrender.com/api/tarefas';

  constructor(private http: HttpClient) { }

  getTasks(filtro?: any): Observable<Task[]> {
    let params = new HttpParams();

    if (filtro) {
      if (filtro.id) params = params.set('id', filtro.id);
      if (filtro.titulo) params = params.set('titulo', filtro.titulo);
      if (filtro.responsavel) params = params.set('responsavel', filtro.responsavel);
      if (filtro.situacao) params = params.set('situacao', filtro.situacao);
    }

    return this.http.get<Task[]>(this.apiUrl, { params });
  }

  getTaskById(id: number): Observable<Task> {
    return this.http.get<Task>(`${this.apiUrl}/${id}`);
  }

  createTask(task: Task): Observable<Task> {
    return this.http.post<Task>(this.apiUrl, task);
  }

  updateTask(task: Task): Observable<Task> {
    return this.http.put<Task>(`${this.apiUrl}/${task.id}`, task);
  }

  deleteTask(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
  
  concludeTask(task: Task): Observable<Task> {
    task.situacao = 'CONCLUIDA';
    return this.updateTask(task);
  }
}