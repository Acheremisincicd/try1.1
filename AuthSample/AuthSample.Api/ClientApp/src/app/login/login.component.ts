import { Component, OnInit, ElementRef } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginModel } from '../models/auth.model';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [AuthService],
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  formData: LoginModel = {
    email: null,
    password: null,
  };
  incorrectLoginOrPassword = false;
  errorMessage: string;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private elementRef: ElementRef
    ) {
      this.errorMessage = 'Incorrect email or password';
    }

  ngAfterViewInit(): void {
    this.elementRef.nativeElement.ownerDocument.body.style.backgroundColor = '#4189C7';
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: [null, Validators.compose([
        Validators.required,
        Validators.pattern('^([a-zA-Z]+[_\\-\\.]*)+@[a-zA-Z]+\\.[a-zA-Z]+$'),
        Validators.maxLength(50)
      ])],
      password: [null, Validators.compose([
        Validators.required,
        Validators.minLength(6),
        Validators.maxLength(30)
      ])]
    });
    this.loginForm.valueChanges.subscribe(data => this.formData = data);
  }

  onLogin() {
    this.loginForm.controls.email.markAsTouched();
    this.loginForm.controls.password.markAsTouched();
    if (this.loginForm.valid) {
      this.incorrectLoginOrPassword = false;
      this.authService.login(this.formData.email, this.formData.password)
        .subscribe(data => {
          this.router.navigate(['']);
        }, err => {
          this.incorrectLoginOrPassword = true;
          this.errorMessage = 'Incorrect email or password';
        });
    } else {
      this.incorrectLoginOrPassword = true;
      if (this.loginForm.controls.email.invalid) {
        this.errorMessage = 'Email not valid';
      } else {
        this.errorMessage = 'Password not valid';
      }
    }
  }
}
