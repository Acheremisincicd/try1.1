import { Component, OnInit } from '@angular/core';
import { HttpService } from '../services/http.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  providers: [HttpService]
})
export class AdminComponent implements OnInit {
  data: string;
  accessDenied: boolean;

  constructor(private httpService: HttpService) {
    this.accessDenied = false;
  }

  ngOnInit() {
    this.httpService.fetchAdminData().subscribe(data => {
      this.data = data;
    }, error => {
      this.accessDenied = true;
    });
  }
}
