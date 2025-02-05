import {Component, inject} from '@angular/core';
import {MatSidenav, MatSidenavContainer, MatSidenavModule} from '@angular/material/sidenav';
import {MatToolbar, MatToolbarModule} from '@angular/material/toolbar';
import {MatListItem, MatListModule, MatNavList} from '@angular/material/list';
import {RouterLink, RouterModule} from '@angular/router';
import {CommonModule} from '@angular/common';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {BooleanInput} from '@angular/cdk/coercion';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {map, shareReplay} from 'rxjs';

@Component({
  selector: 'app-sidebar-cmp',
  imports: [
    RouterModule,
    CommonModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
  ],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent {
  private breakpointObserver= inject(BreakpointObserver);
  isHandset$=this.breakpointObserver.observe([Breakpoints.XSmall]).pipe(
    map((result) => result.matches),
    //shareReplay()
  )

  openMenu: BooleanInput=true;

  toggleMenu(){
    this.openMenu = !this.openMenu;
  }

}
