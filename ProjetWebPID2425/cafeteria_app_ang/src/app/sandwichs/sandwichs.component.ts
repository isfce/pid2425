import {Component, Input} from '@angular/core';
import {Sandwich} from '../sandwich';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {MatCard, MatCardActions, MatCardContent, MatCardImage} from '@angular/material/card';
import {MatButton} from '@angular/material/button';

@Component({
  selector: 'app-sandwichs',
  imports: [CommonModule, RouterModule, MatCard, MatCardContent, MatCardActions, MatButton, MatCardImage,],
  templateUrl: './sandwichs.component.html',
  styleUrl: './sandwichs.component.css',
  standalone:true,
})
export class SandwichsComponent {
  @Input() sandwich!: Sandwich;

  get apiUrl(): string {
    return `images/${this.sandwich.code}.png`;
  }

  addToCart(sandwich: Sandwich) {
    alert("CART: "+sandwich.code);
  }
}
