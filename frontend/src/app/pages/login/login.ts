import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; // Para usar ngModel
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth';
import { UserLogin } from '../../models/auth.model';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './login.html'
})
export class Login {
  loginData: UserLogin = { login: '', senha: '' };
  errorMessage = '';

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit() {
    this.authService.login(this.loginData).subscribe({
      next: () => {
        // Sucesso: vai para as tarefas
        this.router.navigate(['/tasks']);
      },
      error: (err) => {
        // Erro: Mostra mensagem
        this.errorMessage = 'Login ou senha inválidos!';
        console.error(err);
      }
    });
  }
}