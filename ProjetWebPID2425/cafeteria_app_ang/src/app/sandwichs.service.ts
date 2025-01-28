import {inject, Injectable} from '@angular/core';
import {Sandwich} from './sandwich';
import {HttpClient} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {environment} from '../environments/environment';
import {User} from './user';
import {AuthService} from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class SandwichsService {
  http= inject(HttpClient);
  auth= inject(AuthService);

  urlListeSandwichs = `${environment.urlResourceServer}/api/sandwichs?dispo`;
  urlUser = `${environment.urlResourceServer}/api/user/profile/`;

  getAvailableSandwichs():Observable<Sandwich[]> {
    return this.http.get<Sandwich[]>(this.urlListeSandwichs);
  }

  getUserInfo(): Observable<User> {
    if (this.auth.isAuthenticated()) {
      let userId=this.auth.username;
      return this.http.get<User>(`${this.urlUser}${userId}`);
    }
    return of({username: '', email: '', solde: 0});
  }
}
