import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Router } from '@angular/router';
import { EnvService } from './env.service';
import { JwtHelperService } from "@auth0/angular-jwt";


@Injectable({
  providedIn: 'root'
})
export class ApidbService {
  public isLoggedIn: boolean = false;
  token: any;
  currentcrmclient: any = JSON.parse(localStorage.getItem('crmclient') || '{}');

  private currentUserSubject: BehaviorSubject<any>;
  public currentUser: Observable<any>;
  constructor(
    private http: HttpClient,
    private env: EnvService,
    public jwtHelper: JwtHelperService,
    // public storage: Storage,
    private router: Router,
  ) {
    this.currentUserSubject = new BehaviorSubject<any>(
      JSON.parse(localStorage.getItem('currentUser') || '{}')
    );
    this.currentUser = this.currentUserSubject.asObservable();
  }


  private refreshliste_sub = new Subject<void>();

  get refreshliste() {
    return this.refreshliste_sub;
  }
  isLoggedIns(){
    return this.isLoggedIn = true
  }
  public get currentUserValue(): any {
    return this.currentUserSubject.value;
  }
  login(username: string, password: string) {
    var key2 = this.currentcrmclient.key2;

    return this.http
      .post<any>(this.env.API_URL + 'login/', {
        username,
        password,
        'key': key2,
      })
      .pipe(
        tap((user: any) => {
          if (user && user.token) {
            // user.expiresAt = moment().add(user.expiresIn, 'second');
            this.isLoggedIn = true;
            this.currentUserSubject.next(user);
            //user.token="";
            localStorage.setItem('currentUser', JSON.stringify(user));
            localStorage.setItem('token', (user.token));
            const user1 = localStorage.getItem('currentUser');
            return user
          }
        })
      );
  }

  register(username: string, email: string, password: string, password2: string) {
    return this.http
      .post<any>(this.env.API_URL + 'register/', {
        username,
        email,
        password,
        password2
      })
      .pipe(
        tap((returnObj) => {
          return returnObj;
        })
      );
  }

  getpwd() {
    return this.http.get<Element>(this.env.API_URL + 'getpwd').pipe(
      tap((returnObj) => {
        return returnObj;
      })
    );
  }

  logout() {
    localStorage.clear();
    this.currentUserSubject = new BehaviorSubject<any>(null);
    this.isLoggedIn = false;
    delete this.token;
    this.router.navigate(['/']);
    return null;
    // return this.http.get<any>(this.env.API_URL + 'logout').pipe(
    //   tap((data) => {
    //     return data;
    //   })
    // );
  }

  // user() {
  //   var token = localStorage.getItem('token');
  //   return this.http.post<User>(this.env.API_URL + 'get_user', { "token": token }).pipe(
  //     tap((user) => {
  //       user);
  //       return user;
  //     })
  //   );
  // }

  getToken() {
    const currentUser = this.currentUserValue;
    if (currentUser) {
      if (currentUser.token != null) {
        this.isLoggedIn = true;
      } else {
        this.isLoggedIn = false;
      }
      return currentUser;
    }
    return null;
  }

  getList(lien: any, obj: any) {
    return this.http.get<any[]>(this.env.API_URL + lien, obj).pipe(
      tap((returnObj) => {
        return returnObj;
      })
    );
  }

  addEnregistrement(lien: any, obj: any) {
    return this.http.post<any>(this.env.API_URL + lien, obj).pipe(
      tap((returnObj) => {
        return returnObj;
      })
    );
  }

  uploadEnregistrement(lien: any, obj: any, config: any) {
    return this.http.post<any>(this.env.API_URL + lien, obj, config).pipe(
      tap((returnObj) => {
        return returnObj;
      })
    );
  }

  modifierEnregistrement(lien: any, obj: any) {
    return this.http.put<any>(this.env.API_URL + lien, obj).pipe(
      tap((returnObj) => {
        return returnObj;
      })
    );
  }

  public isAuthenticated(): boolean {
    const token: any = localStorage.getItem('token');
    return !this.jwtHelper.isTokenExpired(token);
  }

}
