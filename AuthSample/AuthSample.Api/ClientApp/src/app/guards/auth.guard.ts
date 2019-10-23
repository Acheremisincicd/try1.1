import { CanActivate, Router } from "@angular/router";
import { AuthService } from "../services/auth.service";
import { Injectable, Inject } from "@angular/core";

@Injectable()
export class AuthGuard implements CanActivate {

  constructor(
    private router: Router,
    private authService: AuthService,
    ) {}

  canActivate(): boolean {
    if (this.authService.token) {
      return true;
    } else {
      this.router.navigate(['login']);
      return false;
    }
  }
}