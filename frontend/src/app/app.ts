import { Component } from '@angular/core';
import { RouterOutlet, Router, RouterModule } from '@angular/router'; // Adicione RouterModule
import { CommonModule } from '@angular/common'; // Importante para o *ngIf
import { AuthService } from './services/auth';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CommonModule, RouterModule], // Adicione imports
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class AppComponent {
  constructor(public authService: AuthService, private router: Router) {}

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}