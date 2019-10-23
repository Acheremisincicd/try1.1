import { HttpInterceptor, HttpRequest, HttpHandler, HttpErrorResponse } from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import { catchError } from "rxjs/operators";
import { AuthService } from "../services/auth.service";

export class AuthInterceptor implements HttpInterceptor {
  
  constructor(
    private authService: AuthService,
  ) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<any> {
    if(!window.location.pathname.includes('login')) {
      return next.handle(this.addTokenToRequest(request, this.authService.token))
      .pipe(
        catchError(err => {
          if (err instanceof HttpErrorResponse && (<HttpErrorResponse>err).status === 401) {
            this.authService.logout();
          } else {
            return Observable.throw(err);
          }
        }));
    } else {
      return next.handle(request);
    }
  }

  private addTokenToRequest(request: HttpRequest<any>, token: string): HttpRequest<any> {
    return request.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
  }
}