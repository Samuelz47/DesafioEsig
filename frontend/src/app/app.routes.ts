import { Routes } from '@angular/router';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './services/auth';
import { Login } from './pages/login/login';
import { Register } from './pages/register/register';
import { TaskList } from './pages/task-list/task-list';
import { TaskForm } from './pages/task-form/task-form';

const authGuard = () => {
  const authService = inject(AuthService);
  const router = inject(Router);
  
  if (authService.isLoggedIn()) {
    return true;
  } else {
    router.navigate(['/login']);
    return false;
  }
};

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },

  { path: 'login', component: Login },
  { path: 'register', component: Register },
  
  { 
    path: 'tasks', 
    component: TaskList,
    canActivate: [authGuard] 
  },
  { 
    path: 'tasks/new', 
    component: TaskForm,
    canActivate: [authGuard] 
  },
  { 
    path: 'tasks/edit/:id', 
    component: TaskForm,
    canActivate: [authGuard] 
  }
];