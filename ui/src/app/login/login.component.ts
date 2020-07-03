import { Component, OnInit } from '@angular/core';
import {MessageService} from "../shared/message.service";
import {AuthService} from "../shared/auth.service";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  error: string = null;
  username: string;
  password: string;
  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }

  onSubmit(f: NgForm) {
    console.log(f.valid)
    if (f.valid) {
      this.authService.login(this.username, this.password)
    }
  }
}
