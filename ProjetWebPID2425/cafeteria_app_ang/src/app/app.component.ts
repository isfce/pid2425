import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {MenuComponent} from './menu/menu.component';
import {SidebarComponent} from './sidebar/sidebar.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, MenuComponent, SidebarComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'untitled';
}
