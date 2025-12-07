import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './register.html',
  styleUrl: './register.scss'
})
export class Register {
  // Objeto igual ao RegisterDTO do Java
  registerData = {
    login: '',
    senha: '',
    role: 'USER' // Valor padrão
  };

  errorMessage = '';
  successMessage = '';

  constructor(
    private authService: AuthService, 
    private router: Router
  ) {}

  onSubmit() {
    this.authService.register(this.registerData).subscribe({
      next: () => {
        this.successMessage = 'Conta criada com sucesso! Redirecionando...';
        // Espera 2 segundos e vai pro login
        setTimeout(() => {
          this.router.navigate(['/login']);
        }, 2000);
      },
      error: (err) => {
        this.errorMessage = 'Erro ao criar conta. Tente outro login.';
        console.error(err);
      }
    });
  }
}