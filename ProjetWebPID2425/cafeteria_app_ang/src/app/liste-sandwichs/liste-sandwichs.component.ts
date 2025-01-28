import {Component, inject, OnInit} from '@angular/core';
import {Sandwich} from '../sandwich';
import {SandwichsService} from '../sandwichs.service';
import {MatCard} from '@angular/material/card';
import {SandwichsComponent} from '../sandwichs/sandwichs.component';
import {NgForOf} from '@angular/common';

@Component({
  selector: 'app-liste-sandwichs',
  imports: [

    SandwichsComponent,
    NgForOf
  ],
  templateUrl: './liste-sandwichs.component.html',
  styleUrl: './liste-sandwichs.component.css'
})
export class ListeSandwichsComponent implements OnInit {
   liste:Sandwich[]|undefined;

  private srv=inject(SandwichsService);

  ngOnInit(): void {
    this.srv.getAvailableSandwichs().subscribe(value => this.liste = value,error => console.log(error));
  }


}
