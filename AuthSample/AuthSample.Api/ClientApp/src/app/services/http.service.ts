import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class HttpService {

  constructor(
    private http: HttpClient,
  ) {}

  fetchCommonData(): Observable<string> {
    return this.http.get<string>('/api/data/common');
  }

  fetchAdminData(): Observable<string> {
    return this.http.get<string>('/api/data/restricted');
  }
}
