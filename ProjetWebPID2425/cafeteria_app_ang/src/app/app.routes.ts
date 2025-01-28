import { Routes } from '@angular/router';
import {HomeComponent} from './home/home.component';
import {ListeSandwichsComponent} from './liste-sandwichs/liste-sandwichs.component';
import {inject} from '@angular/core';
import {AuthService} from './auth.service';
import {UserProfileComponent} from './user-profile/user-profile.component';

export const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent,
    title: 'Home',
  },
  {
    canActivate: [() => inject(AuthService).isAuthenticated()],
    path: 'sandwichs',
    component: ListeSandwichsComponent,
    title: 'Liste des Sandwichs',
    children: [


    ]
  },
  {  canActivate: [() => inject(AuthService).isAuthenticated()],
    path: 'profile',
    component: UserProfileComponent,
    title: 'Profile',
  }

];
