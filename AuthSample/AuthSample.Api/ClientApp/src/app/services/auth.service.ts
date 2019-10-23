import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { AuthData } from '../models/auth.model';
import { Observable } from 'rxjs/Observable';
import { map } from 'rxjs/operators';

@Injectable()
export class AuthService {
  private tokenName: string;

  constructor(private http: HttpClient, private router: Router) {
    this.tokenName = 'auth-token';
   }

  get token(): string {
    return localStorage.getItem(this.tokenName);
  }
  set token(val: string) {
    localStorage.setItem(this.tokenName, val);
  }

  login(email: string, password: string): Observable<AuthData> {
    return this.http
      .post<AuthData>('api/auth/login', { email: email, password: password })
      .pipe(
        map((data: AuthData) => {
          this.token = data.securityToken;

          return data;
        }));
  }

  logout() {
    localStorage.removeItem(this.tokenName);
    this.router.navigate(['/login']);
  }
}
