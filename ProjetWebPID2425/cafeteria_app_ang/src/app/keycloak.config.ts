import {
  provideKeycloak,
  createInterceptorCondition,
  IncludeBearerTokenCondition,
  INCLUDE_BEARER_TOKEN_INTERCEPTOR_CONFIG,
  withAutoRefreshToken,
  AutoRefreshTokenService,
  UserActivityService
} from 'keycloak-angular';
import {environment} from '../environments/environment';

const localhostCondition = createInterceptorCondition<IncludeBearerTokenCondition>({
  urlPattern: new RegExp(`^(${environment.scheme}:\/\/${environment.resourceServerHost})(/.*)?$`,"i"),
  bearerPrefix:'Bearer',
});

export const provideKeycloakAngular = () =>
  provideKeycloak({
    config: {
      realm: 'CAFET',
      url: environment.urlAuthServer,
      clientId: 'CAFET',


    },
    initOptions: {
      onLoad: 'check-sso',
      silentCheckSsoRedirectUri: window.location.origin + '/silent-check-sso.html',
      redirectUri: window.location.origin + '/',
      scope:"openid email",
    },

    features: [
      withAutoRefreshToken({
        onInactivityTimeout: 'logout',
        sessionTimeout: 600000
      })
    ],
    providers: [
      AutoRefreshTokenService,
      UserActivityService,
      {
        provide: INCLUDE_BEARER_TOKEN_INTERCEPTOR_CONFIG,
        useValue: [localhostCondition]
      }
    ]
  });
