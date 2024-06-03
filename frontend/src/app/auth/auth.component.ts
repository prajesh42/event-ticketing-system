import { Component } from '@angular/core';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss']
})
export class AuthComponent {
  isLogin:boolean = true;

  constructor(private authService: AuthService) {}

  login(username: string, password: string): void {
    this.authService.login(username, password);
  }

  signup(username: string, password: string): void {
    this.authService.register(username, password);
  }

  toggleView() {
    this.isLogin = !this.isLogin;
  }

  onSubmitLogin() {
    console.log('Login form submitted', this.login);
    // Add your login logic here
  }

  onSubmitSignUp() {
    console.log('Sign Up form submitted', this.signup);
    // Add your sign-up logic here
  }
}
