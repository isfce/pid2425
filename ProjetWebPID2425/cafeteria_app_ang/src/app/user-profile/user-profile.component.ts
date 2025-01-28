import {Component, inject, OnInit} from '@angular/core';
import {User} from '../user';
import {SandwichsService} from '../sandwichs.service';
import {CurrencyPipe} from '@angular/common';
import {MatCard, MatCardContent, MatCardHeader, MatCardTitle} from '@angular/material/card';

@Component({
  selector: 'app-user-profile',
  imports: [
    CurrencyPipe,
    MatCard,
    MatCardHeader,
    MatCardContent,
    MatCardTitle
  ],
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.css'
})
export class UserProfileComponent implements OnInit {
  user: User | undefined;
  sandwichsService: SandwichsService= inject(SandwichsService)

  ngOnInit(): void {
    this.sandwichsService.getUserInfo().subscribe({
      next: (data) => {
        this.user = data; // données de l'utilisateur
      },
      error: (err) => {
        console.error('Erreur lors de la récupération de l\'utilisateur', err);
      }
    });
  }
}
