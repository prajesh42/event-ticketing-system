import { Component } from '@angular/core';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  // styleUrls: ['./auth.component.css']
})
export class AuthComponent {
  constructor(private authService: AuthService) {}

  login(username: string, password: string): void {
    this.authService.login(username, password);
  }

  register(username: string, password: string): void {
    this.authService.register(username, password);
  }
}
