import {Component, OnInit} from '@angular/core';
import {Message} from "../shared/message.model";
import {MessageService} from "../shared/message.service";
import {AuthService} from "../shared/auth.service";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {

  error: string = null;
  currentUser: string;
  messages: Message[];
  newMessage: Message;

  constructor(
    private messageService: MessageService,
    private authService: AuthService) {
  }

  ngOnInit(): void {
    this.currentUser = this.authService.getCurrentUser();
    this.newMessage = new Message(this.currentUser, "", null)
    this.messages = []
    this.messageService.messagesSubscribe().subscribe(
      success => {
        this.error = null;
        this.messages.push(success)
      },
      error => { // second parameter is to listen for error
        console.log(error);
        this.error = "Error getting message list";
      }
    )
  }

  onSubmit(f: NgForm) {
    console.log(f.valid)
    if (f.valid) {
      this.newMessage.origin = this.currentUser;
      this.messageService.sendMessage(this.newMessage).subscribe(
        success => {
          console.log("successfully sent, status = " + success.status)
          this.newMessage.content = "";
        },
        error => { // second parameter is to listen for error
          console.log(error);
          this.error = "Error sending message list";
        }
      );
    }
  }
}
