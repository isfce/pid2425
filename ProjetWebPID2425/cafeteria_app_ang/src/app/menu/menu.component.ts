import {Component, inject} from '@angular/core';
import {RouterModule} from '@angular/router';

import {
  HasRolesDirective,
} from 'keycloak-angular';

import {AuthService} from '../auth.service';

@Component({
  selector: 'app-menu',
  imports: [
    RouterModule, HasRolesDirective,
  ],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css',
  standalone: true,
})
export class MenuComponent {
  private authService: AuthService = inject(AuthService);

  login() {
    this.authService.login();
  }

  logout() {
    this.authService.logout();
  }

  getUsername() {
    return this.authService.username;
  }

  isAuthenticated() {
    return this.authService.isAuthenticated();
  }
}
