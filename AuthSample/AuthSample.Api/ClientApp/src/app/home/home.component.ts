import { Component, OnInit } from '@angular/core';
import { HttpService } from '../services/http.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  providers: [HttpService]
})
export class HomeComponent implements OnInit {
  data: string;

  constructor(private httpService: HttpService) {}

  ngOnInit() {
    debugger;
    this.httpService.fetchCommonData().subscribe(data => {
      this.data = data;
    });
  }
}