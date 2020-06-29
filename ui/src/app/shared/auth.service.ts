import {Injectable} from "@angular/core";
import {Router} from "@angular/router";

@Injectable()
export class AuthService {
  private currentUser: string = null;

  constructor(private router: Router) {
  }

  getCurrentUser() {
    return this.currentUser;
  }

  login(user: string, password: string) {
    this.currentUser = user;
    console.log("user " + user + "logged in");
    this.router.navigate(["/"])
  }

  logout() {
    this.currentUser = null;
  }

  isUserLoggedIn() {
    return new Promise(
      (resolve, reject) =>
        setTimeout(() => resolve(this.currentUser !== null), 500)
    )
  }
}
