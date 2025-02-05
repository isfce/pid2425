import {Component, inject} from '@angular/core';
import {RouterModule} from '@angular/router';

import {
  HasRolesDirective,
} from 'keycloak-angular';

import {AuthService} from '../auth.service';
import { MatIconModule} from '@angular/material/icon';
import {SidebarComponent} from '../sidebar/sidebar.component';

@Component({
  selector: 'app-menu',
  imports: [
    RouterModule, HasRolesDirective, MatIconModule,
  ],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css',
  standalone: true,
})
export class MenuComponent {
  private authService: AuthService = inject(AuthService);
  private sideBar=inject(SidebarComponent);

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

  toggleMenu() {
    this.sideBar.toggleMenu();
  }
}
