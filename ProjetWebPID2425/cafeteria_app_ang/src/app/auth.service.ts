import {effect, inject, Injectable} from '@angular/core';
import Keycloak from 'keycloak-js';
import {KEYCLOAK_EVENT_SIGNAL, KeycloakEventType, ReadyArgs, typeEventArgs} from 'keycloak-angular';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  authenticated = false;
  username: string | undefined="";
  keycloakStatus: string | undefined;
  private readonly keycloak = inject(Keycloak);
  private readonly keycloakSignal = inject(KEYCLOAK_EVENT_SIGNAL);
  userRoles: string[] | undefined;
  constructor() {
    effect(() => {
      const keycloakEvent = this.keycloakSignal();

      this.keycloakStatus = keycloakEvent.type;

      if (keycloakEvent.type === KeycloakEventType.Ready) {
        this.authenticated = typeEventArgs<ReadyArgs>(keycloakEvent.args);
        if (this.authenticated){
          this.keycloak.loadUserProfile().then(userProfile =>
          { this.username=userProfile.username;
            this.userRoles = this.keycloak.realmAccess?.roles || [];
            }
          ).catch(
            error => {console.log(error);this.username="";}
          )

        }
      }

      if (keycloakEvent.type === KeycloakEventType.AuthLogout) {
        this.authenticated = false;
        this.username=""
      }

    });
  }

  isAuthenticated(){
    return this.authenticated;
  }

  getUsername() {

    return this.username??"";
  }

  login() {
    this.keycloak.login();
  }

  logout() {
    this.keycloak.logout();
  }
}
