import { Component, signal, computed, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService, AuthRequest } from '../../../core/services';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  private authService = inject(AuthService);
  private router = inject(Router);

  username = signal('');
  password = signal('');
  error = signal<string | null>(null);
  loading = signal(false);

  isFormValid = computed(() => this.username().trim().length > 0 && this.password().trim().length > 0);

  onSubmit(): void {
    if (!this.isFormValid()) return;

    this.loading.set(true);
    this.error.set(null);

    const credentials: AuthRequest = {
      username: this.username().trim(),
      password: this.password(),
    };

    this.authService.login(credentials).subscribe({
      next: () => {
        this.router.navigate(['/alumnos']);
      },
      error: (err) => {
        this.error.set(err.error?.mensaje ?? 'Credenciales inválidas');
        this.loading.set(false);
      },
    });
  }
}