import { Component } from '@angular/core';
import {MessageService} from "./shared/message.service";
import {AuthService} from "./shared/auth.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [MessageService]
})

export class AppComponent {
  title = 'ui';
}
