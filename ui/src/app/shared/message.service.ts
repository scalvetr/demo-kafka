import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

import {Message} from "./message.model";
import {BehaviorSubject, Observable, Subject} from "rxjs";

@Injectable()
export class MessageService {

  const
  defaultHttpOptions = {};

  constructor(private http: HttpClient) {
  }

  sendMessage(newMessage: Message) {
    newMessage.timestamp = new Date()
    console.log("sendMessage = " + newMessage)
    return this.http.post<any>(environment.messagesService.postMessage, newMessage,
      {
        ...this.defaultHttpOptions, ...{
          observe: "response"
        }
      })
  }

  messagesSubscribe() {
    const eventSource = new EventSource(environment.messagesService.getMessagesStream);
    const events = new Subject<Message>();
    eventSource.addEventListener("error", error => error => events.error(error));
    eventSource.addEventListener("message", message => {
      const data = JSON.parse(message.data) as Message;
      events.next(data);
    });

    return events.asObservable();
  }
}
